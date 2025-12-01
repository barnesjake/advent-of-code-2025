package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func check(e error) {
	if e != nil {
		panic(e)
	}
}

const startingPosition = 50

func main() {
	inputFile, err := os.Open("puzzle_input.txt")
	scanner := bufio.NewScanner(inputFile)
	check(err)

	tmpPos := startingPosition
	accumulator := 0

	for scanner.Scan() {
		line := scanner.Text()
		direction := line[:1]
		distance, err := strconv.ParseInt(line[1:], 10, 64)
		check(err)

		for i := int64(0); i < distance; i++ {
			if direction == "L" {
				tmpPos -= 1
			} else {
				tmpPos += 1
			}
			if tmpPos%100 == 0 {
				accumulator += 1
			}
		}
	}

	fmt.Printf("Output: %d", accumulator)
}
