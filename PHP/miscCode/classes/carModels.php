<?php
    // Class model for a car.
    class Car {
        private $brand;
        private $model;
        private $plate;
        private $color;
        private $kilometers;
        private $year;
        private $owner;
        private $rating;

        // Class constructor.
        public function __construct($carBrand, $carModel, $carPlate, $carColor, $carKilometers, $carYear, $carOwner, $carRating) {
            $this->brand = $carBrand;
            $this->model = $carModel;
            $this->plate = $carPlate;
            $this->color = $carColor;
            $this->kilometers = $carKilometers;
            $this->year = $carYear;
            $this->owner = $carOwner;
            $this->rating = $carRating;
        }

        // --- GET ALL ATTRIBUTES ---
        public function getAllAttr() {
            foreach(get_object_vars($this) as $currVar) {
                echo $currVar . "<br/>";
            }
        }

        // --- BRAND METHODS ---
        public function getBrand() {
            return $this->brand;
        }

        public function setBrand($newBrand) {
            $this->brand = $newBrand;
        }

        // --- MODEL METHODS ---
        public function getModel() {
            return $this->model;
        }

        public function setModel($newModel) {
            $this->model = $newModel;
        }

        // --- PLATE METHODS ---
        public function getPlate() {
            return $this->plate;
        }

        public function setPlate($newPlate) {
            $this->plate = $newPlate;
        }

        // --- COLOR METHODS ---
        public function getColor() {
            return $this->color;
        }

        public function setColor($newColor) {
            $this->color = $newColor;
        }

        // --- KILOMETERS METHODS ---
        public function getKilometers() {
            return $this->kilometers;
        }

        public function setKilometers($newKilometers) {
            $this->kilometers = $newKilometers;
        }

        // --- YEAR METHODS ---
        public function getYear() {
            return $this->year;
        }

        public function setYear($newYear) {
            $this->year = $newYear;
        }

        // --- OWNER METHODS ---
        public function getOwner() {
            return $this->owner;
        }

        public function setOwner($newOwner) {
            $this->owner = $newOwner;
        }

        // --- RATING METHODS ---
        public function getRating() {
            return $this->rating;
        }

        public function setRating($newRating) {
            $this->rating = $newRating;
        }
    }
?>