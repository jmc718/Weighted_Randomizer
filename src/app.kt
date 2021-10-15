/******************************************************************************
WEIGHTED RANDOMIZER
 Makes a weighted random choice between items specified by user input via
 file or console input.
******************************************************************************/

import java.io.File
import java.io.InputStream

/******************************************************************************
Function: randomize
In:
    choicesMap: A Map that contains pairs of a possibility and a likelihood
    totProb: the sum of all likelihoods
Description: Selects a random number and moves from possibility to
 possibility until the random number is found within the range.
Out: The possibility that coincides with a randomly generated number
******************************************************************************/
fun randomize(choicesMap: Map<String, Int>, totProb: Int): String {

    val rand = (1..totProb).random()
    // our random number is where we'll start
    var spinner = rand
    for ((k, v) in choicesMap) {
        // v has a certain probability "range" per se. If it's 3, anything less
        // than or equal to 3 will be in the range.
        // if it is in the range of v, that's the randomly picked option
        if (spinner <= v)
            return k
        // if not, we're moving on to the next one by subtracting the current
        // probability
        else
            spinner -= v
        // keep doing this until we find the right one
    }
    return "You shouldn't be here."

}

/******************************************************************************
Function: readFromFile
Description: This function reads each possibility and likelihood from a file
 in the root directory called "choice.txt". Odd numbered lines will be
 possibilities and the even numbered ones that follow each will be the
 likelihood.
Out: A Map that contains pairs of a possibility and a likelihood
******************************************************************************/
fun readFromFile(): MutableMap<String, Int> {
    // Initialize map to add to
    val choicesMap = mutableMapOf<String,Int>()

    // read in from choice.txt into a List of strings containing each line
    val inputStream: InputStream = File("src/choice.txt").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    var odd = true
    val stringList = mutableListOf<String>()
    val intList = mutableListOf<Int>()
    lineList.forEach{
        // All the odd numbered lines will be our choices
        if (odd) {
            stringList.add(it)
        }
        // All the even numbered lines will be our probabilities
        if (!odd) {
            intList.add(it.toInt())
        }

        // Flip our value
        odd = !odd
    }

    for (n in 0 until intList.size) {
        choicesMap.put(stringList.elementAt(n), intList.elementAt(n))
    }

    println(choicesMap)

    return choicesMap

}
/******************************************************************************
Function: cins
Out: A Map that contains pairs of a possibility and a likelihood
Description: This is used specifically for when the user wants to populate
 the map manually.
******************************************************************************/

fun cins(): MutableMap<String, Int> {

    val choicesMap = mutableMapOf<String,Int>()
    while(true) {
        // The user will be prompted for a thing they want to choose between
        println("Enter a possibility (leave blank to finish)")
        val input = readLine()!!.toString()

        // If the string is empty, we're done: let's move on.
        if (input == "")
            break

        // We'll call this 'int put' because I love puns
        var intput = 0

        while(true) {
            // The user is prompted for a probability
            println("How likely should it be?")


            try {
                intput = readLine()!!.toInt()

                if (intput < 1) {
                    println("Number must be at least 1")
                }

                else {
                    break
                }

            } catch (e: NumberFormatException) {
                println("Error $e")
                println("Invalid input. Must be an integer number.")
            }

        }


        // Put the pair in the map
        choicesMap.put(input, intput)
    }
    return choicesMap
}

/******************************************************************************
Function: randomize
In:
    n: The number of random choices we want to generate
    choicesMap: A Map that contains pairs of a possibility and a likelihood
    totProb: the sum of all likelihoods
Description: Calls Randomize() function n number of times
 ******************************************************************************/
fun rollLotsRands(n: Int, choicesMap: Map<String, Int>, totProb: Int) {
    for(n in 0..n){
        val randSel = randomize(choicesMap, totProb)
        println("random selection $n is $randSel")
    }
}

/******************************************************************************
Function: main
Description: The master function that calls all others
 ******************************************************************************/
fun main() {
//    val x = 100
//
//    println((0..x).random())


    val choicesMap: MutableMap<String, Int>

    while(true) {

        println("Enter 1 to read from a file, enter 2 to input options by hand")
        val input = readLine()!!.toString()

        if (input == "1"){
            choicesMap = readFromFile()
            break
        }
        if (input == "2") {
            choicesMap = cins()
            break
        }


    }

    // Create a variable that will contain total probability
    var totProb = 0
    // Add all probabilities together to get the total
    for ((k, v) in choicesMap) {
        totProb += v
    }

    // generate a random number between 1 and the total probability


    // Loop through each item and see which one has been selected

    val randSel = randomize(choicesMap, totProb)

    println("Your random selection is $randSel")



    // For testing:
    rollLotsRands(10, choicesMap, totProb)
//    println(totProb)
//    println(choicesMap)



}