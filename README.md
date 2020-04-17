# Homework 5 for Epl133
## By Andreas Naziris and Konstantinos Larkos

This is a program for simulating infections.

## People
There are 4 kinds of people in this program.
1. Boomers, old people.
2. Careful people.
3. Normal people.
4. Immune people.


## Invariants
1. All probabilities are between 0 and 1 including those 2 values.
2. Vulnerabilities of people Boomer > Normal > Careful > Immune = 0.
3. Immune people can't get infected.
4. People can move in all direction(including diagonally) but only by one step.
5. Mobility of people Immune > Normal = Careful > Boomer.
6. People can't get out of the grid.
7. People will not get infected if they don't have infected neighbours and don't stand on infected cell.