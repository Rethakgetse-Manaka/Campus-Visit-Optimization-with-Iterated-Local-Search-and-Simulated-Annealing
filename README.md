# Campus-Visit-Optimization-with-Iterated-Local-Search-and-Simulated-Annealing
This repository contains Java implementations of two algorithms, Iterated Local Search (ILS) and Simulated Annealing (SA), designed to find the most efficient route for a student travelling to different campuses. The goal is to minimize the total distance traveled while visiting each campus exactly once and returning to the starting point.

How to Run:
Compile the Java Files:

Make sure you have Java Development Kit (JDK) installed on your system.
Open a command prompt or terminal window.
Navigate to the directory containing the Java files.
Compile the Java files using the javac command. For example:

javac SimulatedAnnealing.java
javac IterativeSearch.java

Prepare Cost Matrix File:

Ensure you have a file named "Cost_Matrix.txt" containing the cost matrix data.
The cost matrix file should be formatted correctly, with distance values between different locations (campuses).
Run the Programs:

After compiling the Java files, run the programs using the java command. For example:
Copy code
java SimulatedAnnealing
java IterativeSearch
Observe the output, including execution time, best tour found, total distance, and average objective function value.
