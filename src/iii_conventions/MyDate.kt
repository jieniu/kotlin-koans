package iii_conventions

import com.sun.org.apache.xpath.internal.operations.Bool

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override operator fun compareTo(other: MyDate): Int = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun MyDate.plus(interval: TimeInterval): MyDate {
    return this.addTimeIntervals(interval, 1)
}

operator fun MyDate.plus(ti: TimeIntervals): MyDate {
    return this.addTimeIntervals(ti.timeInterval, ti.number)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate)
    : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return DateRangeIter(this)
    }

    override fun contains(value: MyDate): Boolean {
        return value >= start && value <= endInclusive
    }
}


class DateRangeIter(val dateRange: DateRange) : Iterator<MyDate> {
    var current: MyDate = dateRange.start

    override fun hasNext(): Boolean {
        return current <= dateRange.endInclusive
    }

    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }
}

operator fun TimeInterval.times(number: Int) = TimeIntervals(this, number)

data class TimeIntervals(val timeInterval: TimeInterval, val number: Int)