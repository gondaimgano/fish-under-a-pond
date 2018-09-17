package get.gondai.demo.models



data class Location(
         var lat:Double,
         var lng:Double,
         var address:String
){
    constructor():this(lat=0.0,lng =0.0,address ="" )
}