package iii_conventions

import util.TODO
import util.doc25

fun todoTask25(): Nothing = TODO(
    """
        Task 25.
        Uncomment the commented line and make it compile. 
        Make all the changes to the file MyDate.kt.

        Tips: Make the class 'MyDate' implement 'Comparable'.
    """,
    documentation = doc25(),
    references = { date: MyDate, comparable: Comparable<MyDate> -> }
)

/*
operator fun MyDate.compareTo(other: MyDate) : Int {
    if (year == other.year) {
        if (month == other.month) {
            return if (dayOfMonth == other.dayOfMonth) 0 else dayOfMonth - other.dayOfMonth
        } else {
            return month - other.month
        }
    }
    return year - other.year
}
*/

operator fun MyDate.compareTo(other: MyDate): Int = when {
    year != other.year -> year - other.year
    month != other.month -> month - other.month
    else -> dayOfMonth - other.dayOfMonth
}



fun task25(date1: MyDate, date2: MyDate): Boolean {
    //todoTask25()
    return date1 < date2
}

