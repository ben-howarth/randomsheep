INSERT INTO WeatherSensor(sensorType) VALUES ('Temperature');
INSERT INTO WeatherSensor( sensorType) VALUES ('Temperature');
INSERT INTO WeatherSensor( sensorType) VALUES ('HUMIDITY');
INSERT INTO WeatherSensor( sensorType) VALUES ('WindSpeed');

INSERT INTO WeatherSensorReading (sensorId, readingValue, readingDateTime, readingType) VALUES (1, 15.5, '2023-06-15 10:00:00', 'TEMPERATURE');
INSERT INTO WeatherSensorReading (sensorId, readingValue, readingDateTime, readingType) VALUES (2, 18.2, '2023-06-15 10:05:00', 'TEMPERATURE');
INSERT INTO WeatherSensorReading (sensorId, readingValue, readingDateTime, readingType) VALUES (3, 100.3, '2023-06-15 10:10:00', 'HUMIDITY');
INSERT INTO WeatherSensorReading (sensorId, readingValue, readingDateTime, readingType) VALUES (3, 10.1, '2023-06-15 10:10:00', 'TEMPERATURE');
INSERT INTO WeatherSensorReading (sensorId, readingValue, readingDateTime, readingType) VALUES (2, 19.2, '2023-06-15 10:15:00', 'TEMPERATURE');
INSERT INTO WeatherSensorReading (sensorId, readingValue, readingDateTime, readingType) VALUES (2, 20.2, '2023-06-15 10:25:00', 'HUMIDITY');
INSERT INTO WeatherSensorReading (sensorId, readingValue, readingDateTime, readingType) VALUES (2, 21.2, '2023-06-15 10:35:00', 'UV_INDEX');
INSERT INTO WeatherSensorReading (sensorId, readingValue, readingDateTime, readingType) VALUES (2, 21.2, '2023-06-15 10:35:00', 'WIND_SPEED');
