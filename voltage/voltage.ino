float vout = 0.0;

float vin = 0.0;

float R1 = 30000.0;

float R2 = 7500.0;

int SPIN = A1;

int value = 0;

 

void setup(){

pinMode(SPIN, INPUT);

 

Serial.begin(9600);

}

 

void loop(){

value = analogRead(SPIN);

vout = (value * 5.0) / 1024.0;

vin = vout / ( R2 / ( R1 + R2) );

 

Serial.println(vin);


 

delay(1000);

}
