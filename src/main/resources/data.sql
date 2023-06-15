CREATE TABLE WeatherSensor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sensorType VARCHAR(50) NOT NULL
);


CREATE TABLE WeatherSensorReading (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sensorId INT,
    readingValue DECIMAL(10, 2) NOT NULL,
    readingDateTime DATETIME NOT NULL,
    FOREIGN KEY (sensorId) REFERENCES WeatherSensor(id)
);

INSERT INTO WeatherSensor(id, sensorType) VALUES (1, 'Temperature');
INSERT INTO WeatherSensor(id, sensorType) VALUES (2, 'Temperature');
INSERT INTO WeatherSensor(id, sensorType) VALUES (3, 'Humidity');
INSERT INTO WeatherSensor(id, sensorType) VALUES (4, 'WindSpeed');

INSERT INTO WeatherSensorReading (sensorId, readingValue, readingDateTime) VALUES (1, 25.5, '2023-06-15 10:00:00');
INSERT INTO WeatherSensorReading (sensorId, readingValue, readingDateTime) VALUES (2, 60.2, '2023-06-15 10:05:00');
INSERT INTO WeatherSensorReading (sensorId, readingValue, readingDateTime) VALUES (3, 1012.3, '2023-06-15 10:10:00');
INSERT INTO WeatherSensorReading (sensorId, readingValue, readingDateTime) VALUES (3, 10.1, '2023-06-15 10:10:00');
