package com.devjeem.tasklist.network.model.orders

import com.google.gson.annotations.SerializedName



data class Data(
    @SerializedName("guest_orders")
    var guestOrders: List<GuestOrder>,
    @SerializedName("tables")
    var tables: List<Any?> = emptyList(),
)

data class GuestOrder(
    @SerializedName("id")
    var id : Int,
    @SerializedName("code")
    var code : String,
    @SerializedName("created_at")
    var createdAt : String,
    @SerializedName("status")
    var status : String,
    @SerializedName("vanue_id")
    var vanueId : Int,
    @SerializedName("table_id")
    var tableId : Int,
    @SerializedName("employee_id")
    var employeeId : Int,
    @SerializedName("reference")
    var reference : Any? = null,
    @SerializedName("innings")
    var innings : String,
    @SerializedName("new_menu")
    var nextMenu : Int,
    @SerializedName("ext_data")
    var extData : List<ExtData>,
    @SerializedName("comment")
    var comment : Any? = null,
    @SerializedName("otf_version")
    var otfVersion : String,
    @SerializedName("charges")
    var charges : Double,
    @SerializedName("service_charge_active")
    var serviceCharge : Int,
    @SerializedName("cash_discount")
    var cashDiscount : Int,
    @SerializedName("discount")
    var discount : Int,
    @SerializedName("items_discount")
    var itemsDiscount : Double,
    @SerializedName("discount_order")
    var discountOrder : Double,
    @SerializedName("service_charge_value")
    var serviceChargeValue: Int,
    @SerializedName("total")
    var total : Double,
    @SerializedName("total_new_version")
    var totalNewVersion : Double,
    @SerializedName("employee")
    var employee : Employee,
    @SerializedName("clients")
    var clients : List<Clients> = emptyList(),
    @SerializedName("order")
    var order : Order,

    )

data class ExtData(
    @SerializedName("split_order")
    var splitOrder: SplitOrder
)

data class SplitOrder(
    @SerializedName("parent_id")
    var parentId: Int,
)

data class Employee(
    @SerializedName("id")
    var id: Long,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("last_name")
    var lastName: Any?,
    @SerializedName("type")
    var type: String,
    @SerializedName("pin")
    var pin: String,
    @SerializedName("access_ground_control")
    var accessGroundControl: Int,
    @SerializedName("email")
    var email: Any? = null,
    @SerializedName("mobile")
    var mobile: String,
    @SerializedName("address")
    var address: Any? = null,
    @SerializedName("country")
    var country: Any? = null,
    @SerializedName("state")
    var state: Any? = null,
    @SerializedName("city")
    var city: Any? =  null,
    @SerializedName("zip")
    var zip: Any? = null,
    @SerializedName("active")
    var active: Int,
    @SerializedName("device_id")
    var deviceId: Any?,
    @SerializedName("venue_id")
    var venueId: Int,
    @SerializedName("employee_role_id")
    var employeeRoleId: Int,
    @SerializedName("employee_group_id")
    var employeeGroupId: Int,
    @SerializedName("multiple_menu_id")
    var multipleMenuId: Any? = null,
    @SerializedName("user_7shifts")
    var user7shifts: Int,
    @SerializedName("editable")
    var editable: Any? = null,
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("updated_at")
    var updatedAt: String,
    @SerializedName("deleted_at")
    var deletedAt: Any? = null,
)

data class Clients(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("first_name")
    var firstName: String? = null,
    @SerializedName("last_name")
    var lastName: String? = null,
    @SerializedName("company")
    var company: Any? = null,
    @SerializedName("address")
    var address: Any? = null,
    @SerializedName("zipcode")
    var zipcode: Any? = null,
    @SerializedName("state")
    var state: Any? = null,
    @SerializedName("city")
    var city: Any? = null,
    @SerializedName("phone")
    var phone: Any? = null,
    @SerializedName("comments")
    var comments: Any? = null,
    @SerializedName("mobile")
    var mobile: String,
    @SerializedName("ext")
    var ext: Any? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("ig_user")
    var igUser: Any? = null,
    @SerializedName("created_by")
    var createdBy: Any? = null,
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("updated_at")
    var updatedAt: String,
    @SerializedName("pivot")
    var pivot: Pivot,
)

data class Pivot(
    @SerializedName("order_id")
    var orderId: Int,
    @SerializedName("client_id")
    var clientId: Int,
)

data class Order(
    @SerializedName("id")
    var id: Int,
    @SerializedName("payment_request")
    var paymentRequest: Any? = null,
)
