
// https://www.youtube.com/watch?v=MGTQWV1VfWk


fun randomize() {



}

fun main() {
//    val x = 100
//
//    println((0..x).random())

    // Let's create a map that contains our choices and the likelihood of each
    val choicesMap = mutableMapOf<String,Int>()

    while(true) {
        // The user will be prompted for a thing they want to choose between
        println("Enter a possibility (leave blank to finish)")
        var input = readLine()!!.toString()

        // If the string is empty, we're done: let's move on.
        if (input == "")
            break

        // The user is prompted for a probability
        println("How likely should it be?")
        // We'll call this 'int put' because I love puns
        var intput = readLine()!!.toInt()
        // TODO: Add error checking to make sure the input is an int

        // Put the pair in the map
        choicesMap.put(input, intput)

    }

    // Create a variable that will contain total probability
    var totProb = 0
    // Add all probabilities together to get the total
    for ((k, v) in choicesMap) {
        totProb += v
    }

    // generate a random number between 1 and the total probability
    var rand = (1..totProb).random()

    // Loop through each item and see which one has been selected

    // For testing:
    println(totProb)
    println(choicesMap)



}