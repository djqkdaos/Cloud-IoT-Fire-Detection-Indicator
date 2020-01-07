# -*- coding: utf-8 -*- 
from gtts import gTTS
import RPi.GPIO as GPIO
import time
import commands
import socket
import os
import sys
import serial
from threading import Thread
import requests
#json module
import json
from collections import OrderedDict
import schedule

reload(sys)
sys.setdefaultencoding('utf-8')

GPIO.setmode(GPIO.BCM)

GPIO.setup(5, GPIO.IN)
GPIO.setup(6 , GPIO.IN)
GPIO.setup(4 , GPIO.IN)
GPIO.setup(14 , GPIO.IN)
GPIO.setup
GPIO.setup(23 , GPIO.OUT)
GPIO.setup(24 , GPIO.OUT)
GPIO.setup(25 , GPIO.OUT)
GPIO.setup(21 , GPIO.OUT)
main_menu = 5
select = 6
up = 4
down = 14
bell = 21
print "Press the button"
menucount = 0
menupoint = 0
#network variable
networkpoint = 0
ip = 0
a = 0
b = 0
c = 0
d = 0
whoset = 0
iptmp = 0
sleepset = 0
subpoint = 0
ipaddrs = ""
subnets = ""
gateways= ""
#------------
#testmode variable
testmodepoint = 0
gamzigi = 0;
plag = True
#----------------------
#status variable
statuspoint = 0
#gamzigiset
gamtmp = 0
#---------------------
#gamzigi general
generalpoint = 0
gamgizicnt = 0
brodcaston = True
broad_onoff_point = 0 
server_point = 0
reset_point = 0
rt = []
#--------------
def send_json():
	global gamgizicnt
	global gamzigi
	f = open('/etc/raspbeery_uuid.conf','r')
	raspb_uuid = json.load(f)
	f.close()
	f = open('/etc/gamzigi_uuid.conf','r')
	gamzigi_uuid = json.load(f)
	f.close()
	s_uuid = []
	gamzigiget()
	for i in gamzigi_uuid:
		s_uuid.append(gamzigi_uuid[i])
	json_data = {"uuid":raspb_uuid['uuid1'],"detection_cnt":gamgizicnt,"s_uuid":s_uuid}
	url = "http://kiraon.mrsshr.net/piadd"
	headers = {'Content-Type': 'application/json; charset=utf-8'}

	res = requests.post(url, headers = headers, data = json.dumps(json_data, indent=2))
	print res.status_code

	

def send_status():
	global gamzigi
	f = open('/etc/raspbeery_uuid.conf','r')
	raspb_uuid = json.load(f)
	f.close()
	f = open('/etc/gamzigi_uuid.conf','r')
	gamzigi_uuid = json.load(f)
	gamzigiget()
	data = []
	cnt = 0
	for i in gamzigi_uuid:
		status = rt[cnt]
		temp = {"s_uuid":i,"status":status}
		data.append(temp)
		cnt+=1
		
	
	json_data = {"uuid":raspb_uuid['uuid1'],"detectcnt":gamgizicnt,"data":data}
	url = "http://kiraon.mrsshr.net/raspbedata"
	headers = {'Content-Type': 'application/json; charset=utf-8'}

	res = requests.post(url, headers = headers, data = json.dumps(json_data, indent=2))
	print res.status_code

schedule.daemon = True
schedule.every(20).seconds.do(send_status)

def arduino():
	global gamzigi
	global plag
	global rt
	print "aruino1"
	try:
		ser = serial.Serial('/dev/ttyUSB0', 9600)
		print "usb0"
	except :
		try:		
			ser = serial.Serial('/dev/ttyUSB1', 9600)
		except:
			print "no detect usb"
			GPIO.output(20,True)
			plag=False
		print "usb1"
	gamzigiget()
	stcnt=0
	stsend=0
	for c in range(0,99):
		rt.append(0)
	
	while plag:
		GPIO.output(20,True)
		time.sleep(0.1)
		GPIO.output(20,False)
		gam = ser.readline().strip()
		sgam = gam.split('@')
		sgam = gam.split('@')
		stcnt=0
		
		for ga in range(0,gamgizicnt):
			if 13.00 > float(sgam[ga]) :
				rt[ga]='nomal'
				stcnt+=1
			elif float(sgam[ga]) < 13.00:
				print gam
				rt[ga] = 'fire'
			else:
				rt[ga]='error'
				stcnt+=1
		if stcnt==gamgizicnt :
			gamzigi=0
			stsend=0
		else:
			gamzigi=1
			stsend+=1
		if stsend==1:
			send_status()
t = Thread(target=arduino)
t.daemon=True
t.start()

		
def mainm(key):
	global menucount
	global menupoint
	global plag
	time.sleep(0.15)
	if GPIO.input(main_menu) == 0 :
		
		menucount = 0
		
	if GPIO.input(up)== 0 :
		if menucount == 0 :
			
			menucount = 3
			
		else:
			
			menucount -= 1
			
	elif GPIO.input(down) == 0 :
		if menucount == 3 :
			
			menucount = 0
			
		else:
			
			menucount +=1
			
	elif GPIO.input(select) == 0 :
		if menucount == 0 :
			menucount = 0
			menupoint = 1
			
			print "Status Menu"
			
		elif menucount == 1 :
			menucount = 0
			menupoint = 2
			
			print "NetWork Menu"
		
		elif menucount == 2 :
			menucount = 0
			menupoint = 3
			
			print "Test Menu"
		
		elif menucount == 3 :
			menucount = 0
			menupoint = 4
			
			print "General Menu"

	if menupoint == 0:
		if GPIO.input(up)==0 or GPIO.input(down)==0 or GPIO.input(main_menu)==0 or GPIO.input(select)==0 :
			if menucount==0 :
				
				print "Status Check"
				
			elif menucount == 1:
				
				print "NetWork"
				
			elif menucount == 2:
				
				print "testmode"
			
			elif menucount == 3:
				
				print "general"
				
			else :
				
				print "err"
			

def status(key):
	global menucount
	global menupoint
	global statuspoint
	time.sleep(0.15)
	if GPIO.input(main_menu) == 0 :
		menupoint = 0
		print "Main Menu"		
	if GPIO.input(up) == 0 :
		if menucount == 0 :	
			menucount = 1
		else:
			menucount -= 1
			
	elif GPIO.input(down) == 0 :
		if menucount == 1 :
			
			menucount = 0
			
		else:
			
			menucount +=1
	elif GPIO.input(select) == 0 :
		if menucount == 0:
			print "detec_stat_chk"
			statuspoint = 1
		elif menucount == 1:
			print "network_stat_chk"
			statuspoint = 2
	else :
		statuspoint = 0
	if menupoint == 1:
		if GPIO.input(up)==0 or GPIO.input(down)==0:
			if menucount==0 :
				
				print "detection_Status"
				
			elif menucount == 1:
				
				print "NetWork_Status"

			else :
				
				print "err"


def networkstatus(key):
	global menupoint
	global statuspoint
	
	time.sleep(0.15)
	
	if GPIO.input(main_menu) == 0 :
		menupoint = 0
		statuskpoint = 0
		print "Main Menu"
	elif GPIO.input(select) == 0 :
		try:
			url = "http://kiraon.mrsshr.net/now"
			response = requests.post(url=url)
			print (response.text)
	
			if response.status_code == 200 :
				print "NetWork OK"
			else :
				print "NetWork ERROR"
		except:
			print "NetWork ERROR"
			print "press main_btn"

def  networkm(key):
	
	global menucount
	global menupoint
	global networkpoint
	global ip
	time.sleep(0.15)
	if GPIO.input(main_menu) == 0 :
		menupoint = 0
		print "Main Menu"
	if GPIO.input(up) == 0 and networkpoint == 0:
		if menucount == 0 :	
			menucount = 2
		else:
			menucount -= 1
		
	elif GPIO.input(down) == 0 and networkpoint ==0:
		if menucount == 2 :
			
			menucount = 0
			
		else:
			
			menucount +=1
	elif GPIO.input(select) == 0 :
		if menucount==0 :
			#ip = commands.getoutput('hostname -I')
			#print ip
			networkpoint = 1
			print "ipcheck_mode"
		elif menucount==1 :
			networkpoint = 2
			print "setIP_mode"
	else:
		networkpoint = 0

					
	if menupoint == 2:
		if GPIO.input(up)==0 or GPIO.input(down)==0 or GPIO.input(main_menu)==0 :
			if menucount==0 :
				
				print "IP address check"
				
			elif menucount == 1:
				
				print "set_IPaddress"
				
			elif menucount == 2:
				
				print "ap_mode"
				
			else :
				
				print "err"

				
			
			
def ipcheck(key):
	'''
	if GPIO.input(main_menu) == 0 :
		menupoint = 0
		print "Main Menu"
	s = socket.socket(socket.AF_INET, socket.S0K_DGRAM)
	s.connect(('8.8.8.8',1))
	ipAddress = s.getsockname()[0]
	return ipAddress
	'''
	global menupoint
	global networkpoint
	global ip
	time.sleep(0.15)
	ip = commands.getoutput('hostname -I')
	if GPIO.input(main_menu) == 0 :
		menupoint = 0
		networkpoint = 0
		print "Main Menu"
	elif GPIO.input(select) == 0 :
		print ip
	
	
def printip(a,b,c,d,whoset,iptmp):
	if whoset == 0:
		a=iptmp
	if whoset == 1:
		b=iptmp
	if whoset == 2:
		c=iptmp
	if whoset == 3:
		d=iptmp
		
	if a < 10 :
		temp="  "+str(a)
	elif a < 100 :
		temp=" "+str(a)
	else:
		temp=""+str(a)
	temp+="."
	if b < 10 :
		temp+="  "+str(b)
	elif b < 100 :
		temp+=" "+str(b)
	else:
		temp+=""+str(b)
	temp+="."
	if c < 10 :
		temp+="  "+str(c)
	elif c < 100 :
		temp+=" "+str(c)
	else:
		temp+=""+str(c)
	temp+="."
	if d < 10 :
		temp+="  "+str(d)
	elif d < 100 :
		temp+=" "+str(d)
	else:
		temp+=""+str(d)
	print (temp)

def setipadd(key):
	global menupoint
	global networkpoint
	global a
	global b
	global c
	global d
	global whoset
	global iptmp
	global sleepset
	global subpoint
	global ipaddrs
	global subnets
	global gateways
	#time.sleep(0.1)
	if GPIO.input(main_menu) == 0 :
		menupoint = 0
		networkpoint = 0
		a=0
		b=0
		c=0
		d=0
		whoset = 0
		iptmp = 0
		sleepset = 0
		subpoint = 0
		print "Main Menu"
	btnbool = False
		
	if GPIO.input(up) == 0 :
		if iptmp == 255:
			iptmp = 0
		else :
			iptmp+=1
		printip(a,b,c,d,whoset,iptmp)
		time.sleep(0.5)
		if GPIO.input(up) == 0 :
			btnbool=True
			atmp=0
			while btnbool :
				if GPIO.input(up) == 0 :
					atmp+=1
					if atmp < 20 :
						iptmp += 1
					else :
						iptmp +=10
					if iptmp > 255:
						iptmp = 0
					printip(a,b,c,d,whoset,iptmp)
					time.sleep(0.1)
				else:
					btnbool=False
					atmp=0
	elif GPIO.input(down) == 0 :
		if iptmp == 0:
			iptmp = 255
		else :
			iptmp-=1
		printip(a,b,c,d,whoset,iptmp)
		time.sleep(0.5)
		if GPIO.input(down) == 0 :
			btnbool=True
			atmp=0
			while btnbool :
				if GPIO.input(down) == 0 :
					atmp-=1
					if atmp < 20 :
						iptmp -= 1
					else :
						iptmp -=10
					if iptmp < 0:
						iptmp = 255
					printip(a,b,c,d,whoset,iptmp)
					time.sleep(0.1)
				else:
					btnbool=False
					atmp=0
	time.sleep(0.3)
	sleepset=1
	if GPIO.input(select) == 0 and sleepset == 1:
		if whoset == 0 :
			a = iptmp
			whoset+=1
			iptmp=0
		elif whoset == 1 :
			b = iptmp
			whoset+=1
			iptmp=0
		elif whoset == 2 :
			c = iptmp
			whoset+=1
			iptmp=0
		elif whoset == 3 :
			d = iptmp
			iptmp=0
			whoset=0
			if subpoint == 0:
				ipaddrs=str(a)+"."+str(b)+"."+str(c)+"."+str(d)
				print "add_ip"
			elif subpoint == 1 :
				subnets=str(a)+"."+str(b)+"."+str(c)+"."+str(d)
				print "add_subnet"
			elif subpoint == 2 :
				gateways=str(a)+"."+str(b)+"."+str(c)+"."+str(d)
				print "add_gateway"
				ipset(ipaddrs, subnets, gateways)
				print "ip set sucess"
				time.sleep(1)
				menupoint = 0
			else :
				printip(a,b,c,d,whoset,iptmp)
			subpoint+=1
			a=0
			b=0
			c=0
			d=0
		time.sleep(0.3)

def ipset(ipaddr, subnet, gateway):
	f = open('/etc/dhcpcd.conf', 'w')
	infos =[
	'hostname\n\npersistent\n\noption rapid_commit\n\noption domain_name_servers, domain_name, domain_search, host_name\n\noption classless_static_routes\n\n',
	'option ntp_servers\n\noption interface_mtu\n\nrequire dhcp_server_identifier\n\n',
	'interface eth0\n',
	'static ip_address='+ipaddr,
	'\nstatic routers='+gateway,
	'\nstatic domain_name_servers=8.8.8.8',
	'\nstatic netmask='+subnet,
	'\nslaac private\n\n',
	'interface wlan0'
	'\nstatic ip_address='+ipaddr,
	'\nstatic routers='+gateway,
	'\nstatic domain_name_servers=8.8.8.8',
	'\nstatic netmask='+subnet,'\n']
	f.writelines(infos) 
	print infos
		
def testmode(key):
	global menucount
	global menupoint
	global testmodepoint
	time.sleep(0.15)
	if GPIO.input(main_menu) == 0 :
		menupoint = 0
		print "Main Menu"
	if GPIO.input(up) == 0 :
		if menucount == 0 :	
			menucount = 2
		else:
			menucount -= 1
			
	elif GPIO.input(down) == 0 :
		if menucount == 2 :
			
			menucount = 0
			
		else:
			
			menucount +=1
	elif GPIO.input(select) == 0 :
		if menucount == 0:
			testmodepoint = 1
			print "Broad_T_mode"
		elif menucount == 1:
			testmodepoint = 2
			print "Broad_T_mode"
	if menupoint == 3:
		if GPIO.input(up)==0 or GPIO.input(down)==0 or GPIO.input(main_menu)==0 :
			if menucount==0 :
				
				print "broadcast_test"
				
			elif menucount == 1:
				print "Buzzer_test"
				
					
				
			elif menucount == 2:
				
				print "detection_test"
				
			else :
				
				print "err"
				
def broadcast_test(key):
	global menupoint
	global testmodepoint
	if GPIO.input(main_menu) == 0 :
		menupoint = 0
		testmodepoint = 0
		print "Main Menu"
	elif GPIO.input(select) == 0:
		
		
		print "Play"
		tts = gTTS(text=unicode("화재발생! 화재발생! 대피 하시기 바랍니다!"), lang='ko')
		tts.save("/home/pi/Desktop/hello.mp3")
		commands.getoutput('omxplayer /home/pi/Desktop/hello.mp3')

		
def buzzertest(key):
	global menupoint
	global testmodepoint
	if GPIO.input(main_menu) == 0 :
		menupoint = 0
		testmodepoint = 0
		print "Main Menu"
	elif GPIO.input(select) == 0:
		GPIO.output(bell,True)
	else :
		GPIO.output(bell,False)

def gamzigiset(key):
	global menupoint
	global generalpoint
	time.sleep(0.25)
	global gamtmp
	if GPIO.input(main_menu) == 0 :
		menupoint = 0
		generalpoint = 0
		print "Main Menu"
	if GPIO.input(up) == 0 :
		gamtmp+=1
		print gamtmp
	elif GPIO.input(down) == 0 :
		if gamtmp == 0:
			gamtmp=0
			print gamtmp
		else:
			gamtmp-=1
			print gamtmp
	if GPIO.input(select) == 0 :
		f = open('/etc/gamzigi.conf', 'w')
		temp = str(gamtmp)
		f.writelines(temp)
		f.close()
		f = open('/etc/gamzigi_uuid.conf', 'w')
		url = "http://kiraon.mrsshr.net/getuuid?num="
		response = requests.get(url+temp)
		
		detec_uuid = json.dumps(response.json(), indent=2)
		json.loads(detec_uuid)
		f.writelines(detec_uuid)
	
		
		print "done!"
		
	
def gamzigiget():
	global gamgizicnt
	global gamtmp
	f = open('/etc/gamzigi.conf','r')
	temp = f.readline().strip()
	gamgizicnt = int(temp)
	
	gamtmp=gamgizicnt
	f.close()
	return gamgizicnt
	
	
	

def gamgizistat():
	#network submit code create plz!!
	#server create pil yo~
	print "yet"
def broadcast_chk(tmp_point2):
	
	if tmp_point2 == 0 :
		print "ON"
	elif tmp_point2 == 1 :
		print "OFF"
	
def broadcast_set(key):
	global brodcaston
	global generalpoint
	global menupoint
	global broad_onoff_point
	time.sleep(0.15)
	if GPIO.input(main_menu) == 0 :
		menupoint = 0
		generalpoint = 0
		print "Main Menu"
	
	
	if GPIO.input(up) == 0 :
		if broad_onoff_point == 0 :
			print "OFF"
			broad_onoff_point = 1
		else:
			print "ON"
			broad_onoff_point = 0
			
	elif GPIO.input(down) == 0 :
		if broad_onoff_point == 0 :
			print "OFF"
			broad_onoff_point = 1
		else:
			print "ON"
			broad_onoff_point = 0
	if GPIO.input(select) == 0 :
		if broad_onoff_point == 0 :
			brodcaston=True
		else :
			brodcaston=False
	
	#here create plz
	#니가 구성하는 메뉴 구조를 몰라서
def reset(key):
	global menupoint
	global generalpoint
	global reset_point
	time.sleep(0.15)
	if GPIO.input(main_menu) == 0 :
		menupoint = 0
		generalpoint = 0
		print "Main Menu"
	 
	
	if GPIO.input(up) == 0 :
		if reset_point == 0 :
			print "YES"
			reset_point = 1
		else :
			print "NO"
			reset_point = 0
			
	elif GPIO.input(down) == 0 :
		if reset_point == 0 :
			print "YES"
			reset_point = 1
		else :
			print "NO"
			reset_point = 0
	if GPIO.input(select) == 0 :
		if reset_point == 0 :
			commands.getoutput('sudo rm -f /etc/gamizgi.conf')
			infos =[
			'hostname\n\npersistent\n\noption rapid_commit\n\noption domain_name_servers, domain_name, domain_search, host_name\n\noption classless_static_routes\n\n',
			'option ntp_servers\n\noption interface_mtu\n\nrequire dhcp_server_identifier\n\n']
			f = open('/etc/dhcpcd.conf', 'w')
			f.writelines(infos)
			commands.getoutput('sudo reboot')
		else :
			menupoint = 0
			generalpoint = 0
			print "Main Menu"
		
	
def add_server(key):
	global menupoint
	global generalpoint
	time.sleep(0.15)
	global server_point
	if GPIO.input(main_menu) == 0 :
		menupoint = 0
		generalpoint = 0
		print "Main Menu"
	
	
	if GPIO.input(up) == 0 :
		if server_point == 0 :
			print "YES"
			server_point = 1
		else :
			print "No"
			server_point = 0
	elif GPIO.input(down) == 0 :
		if server_point == 0 :
			print "YES"
			server_point = 1
		else :
			print "No"
			server_point = 0
	if GPIO.input(select) == 0 :
		if server_point == 1:
			send_json()
			print "done!"
		else:
			menupoint = 0
			generalpoint = 0
			print "Main Menu"
		

	
def general(key):
	global menucount
	global menupoint
	global generalpoint
	time.sleep(0.15)
	if GPIO.input(main_menu) == 0 :
		menupoint = 0
		print "Main Menu"
	if GPIO.input(up) == 0 :
		if menucount == 0 :	
			menucount = 3
		else:
			menucount -= 1
			
	elif GPIO.input(down) == 0 :
		if menucount == 3 :
			
			menucount = 0
			
		else:
			
			menucount +=1
	elif GPIO.input(select)==0:
		if menucount == 0 :
			print "broadcast on?"
			generalpoint = 1
			
		elif menucount == 1 :
			generalpoint = 2
		elif menucount == 2 :
			print "Are you Reset?"
			generalpoint = 3
		elif menucount == 3 :
			print "add sever?"
			generalpoint = 4
	if menupoint == 4:
		if GPIO.input(up)==0 or GPIO.input(down)==0 :
			if menucount==0 :
				
				print "set_broadCast"
				
			elif menucount == 1:
				
				print "set_number_Detec"
				
			elif menucount == 2:
				
				print "reset"
				
			elif menucount == 3:
				
				print "add_server"
				
			else :
				
				print "err"
				
try:
	rasf = open('/etc/raspbeery_uuid.conf','r')
	raspb_uuid_file = json.load(rasf)
	if raspb_uuid_file == "" or None:
		rasf = open('/etc/raspbeery_uuid.conf','w')
		url_raspb_uuid = "http://kiraon.mrsshr.net/getuuid?num=1"
		response_ras_uuid = requests.get(url_raspb_uuid)
		raspb_uuid = json.dumps(response_ras_uuid.json(), indent=2)
		json.loads(raspb_uuid)
		rasf.writelines(raspb_uuid)
		print "raspberry_uuid: " + raspb_uuid['uuid1']
		rasf.close()
	else:
		print "raspberry_uuid: " + raspb_uuid_file['uuid1']
		rasf.close()
	
	
	
	while True:
		
		schedule.run_pending()
		if gamzigi == 1:
			GPIO.output(bell,True)
			#http server signal submit!!!
			#brodcast code!!!!
		else:
			GPIO.output(bell,False)
		if menupoint == 0 :
			mainm(GPIO)
		elif menupoint == 1 :
			if statuspoint == 0:
				status(GPIO)
			elif statuspoint == 1:
				gamgizistat(GPIO)
			elif statuspoint == 2:
				networkstatus(GPIO)
			
		elif menupoint == 2 :
			if networkpoint == 0 :
				networkm(GPIO)
			elif networkpoint == 1:
				ipcheck(GPIO)
			elif networkpoint == 2:
				setipadd(GPIO)
		elif menupoint == 3 :
			if testmodepoint == 0 :
				testmode(GPIO)
			elif testmodepoint == 1 :
				broadcast_test(GPIO)
			elif testmodepoint == 2 :
				buzzertest(GPIO)
			
		elif menupoint == 4 :
			if generalpoint == 0 :
				general(GPIO)
			elif generalpoint == 1:
				broadcast_set(GPIO)
			elif generalpoint == 2:
				gamzigiset(GPIO)
			elif generalpoint == 3:
				reset(GPIO)
			elif generalpoint == 4:
				add_server(GPIO)
except KeyboardInterrupt:
	plag=False
	GPIO.cleanup()
	