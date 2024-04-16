package com.rm.easycart.utils

class AppConstants {
    companion object {
        const val EMPTY_CART: String="cart is empty"
        const val DB_ERROR: String="DB Error"
        const val DB_SUCCESS: String="DB Success"
        const val SUCCESS_CODE = 200
        const val BASE_URL = "https://dummyjson.com/"

        const val DATABASE_NAME = "product_database"
        const val TABLE_PRODUCT = "product"
        const val EMPTY_STRING=""
        const val LIMIT="limit"
        const val SKIP="skip"
        const val PID="pid"

        //coil header
        const val USER_AGENT = "User-Agent"
        const val USER_AGENT_VALUE = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36"


        //Status
        const val API_SUCCESS = "Success"
        const val API_FAILED = "Error"
        const val INFO = "Info"

        //paging
        const val MAX_ITEM_PER_PAGE=10
        const val START_PAGE_INDEX=0



    }
}