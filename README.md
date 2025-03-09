# Avaj-Launcher

## Overview

Avaj Launcher is a Java-based simulation program that models the movement of various aircraft in a virtual environment. Built with Object-Oriented Programming (OOP) principles, this project is designed to reinforce understanding of UML diagrams, design patterns, and Java development. The simulation demonstrates how different aircraft types (balloons, jets, and helicopters) behave under changing weather conditions, managed by a central weather tower.

## Features

- **Aircraft Simulation**: Simulates the behavior of various aircraft types (balloons, jets, and helicopters) following predefined flight rules.
  
- **Dynamic Weather System**: Uses a weather tower to update aircraft behavior based on weather changes.

- **Design Patterns**:
  - **Factory Pattern**: Utilized to create different types of aircraft.
  - **Singleton Pattern**: Ensures a single instance of the weather provider for consistent weather updates across the simulation.
  - **Observer Pattern**: Manages interactions between aircraft and the weather tower, allowing aircraft to react to weather changes.

- **File-Based Input/Output**:
  - Reads simulation scenarios from an input file.
  - Logs simulation results to `simulation.txt`.

## Usage
- **Make** to compile.
- **Make run** to use launch with the scenario located at `scenarios/scenario.txt`.
- **Make fclean** to remove compilation and output files.
