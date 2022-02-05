package com.newsapp.result

enum class ErrorType {
    UNKNOWN {
        override fun value() = -2
    },
    IGNORE {
        override fun value() = -1
    },
    ERROR {
        override fun value() = 0
    },
    NO_INTERNET_CONNECTION {
        override fun value() = 1
    },
    NO_SERVER_CONNECTION {
        override fun value() = 2
    },
    NO_RESULTS_FOUND {
        override fun value() = 3
    };
    abstract fun value(): Int
}
