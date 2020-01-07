<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <title>leaflet-pulse-icon</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.3/leaflet.css" />
    <script src="http://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.3/leaflet.js"></script>

    <script src="https://rawgit.com/mapshakers/leaflet-icon-pulse/master/src/L.Icon.Pulse.js"></script>
    <link rel="stylesheet" href="https://rawgit.com/mapshakers/leaflet-icon-pulse/master/src/L.Icon.Pulse.css" />

    <style>
        #map { position: absolute; top: 0; left: 0; width: 100%; height: 100%; }
        body { font: 16px/1.4 "Helvetica Neue", Arial, sans-serif; }
        a { color: #0077ff; }
    </style>


<div id="map"></div>



<script>

    var map = L.map('map').setView([35.134032,129.1031735], 15);

    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    var pulsingIcon = L.icon.pulse({iconSize:[30,30],color:'red'});
    L.marker([35.134032,129.1031735],{icon: pulsingIcon,title: 'This is pulsing icon'}).addTo(map);

    var pulsingIcon2 = L.icon.pulse({iconSize:[8,8],color:'blue'});
    L.marker([35.134032,129.1131770],{icon: pulsingIcon2,title: 'This is pulsing icon'}).addTo(map);

    var pulsingIcon3 = L.icon.pulse({iconSize:[12,12],color:'green'});
    L.marker([35.134032,129.1231800],{icon: pulsingIcon3,title: 'This is pulsing icon'}).addTo(map);


</script>
