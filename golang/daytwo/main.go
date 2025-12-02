package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func check(e error) {
	if e != nil {
		panic(e)
	}
}

func main() {
	inputFile, err := os.Open("puzzle_input.txt")
	scanner := bufio.NewScanner(inputFile)
	check(err)

	accumulator := int64(0)

	for scanner.Scan() {
		line := scanner.Text()
		ranges := strings.Split(line, ",")
		for i := range len(ranges) {
			tmp := strings.Split(ranges[i], "-")
			start, err := strconv.ParseInt(tmp[0], 10, 64)
			check(err)
			end, err := strconv.ParseInt(tmp[1], 10, 64)
			check(err)
			fmt.Println("Range:", tmp)
			fmt.Println("Start:", start)
			fmt.Println("End:", end)

			numbers := make([]int64, end-start+1)
			for n := range numbers {
				numbers[n] = int64(n) + start
			}
			fmt.Println("numbers: ", numbers)

			for _, v := range numbers {
				fmt.Println("testing number:", v)
				if isValidId(v) {
					accumulator += v
				}
			}
		}
	}

	fmt.Printf("Output: %d", accumulator)
}

func isValidId(id int64) bool {
	str := strconv.FormatInt(id, 10)
	strLen := len(str)
	midPoint := strLen / 2
	if strLen%2 == 0 {
		start := str[:midPoint]
		end := str[midPoint:]
		return start == end
	} else {
		return false
	}

}
