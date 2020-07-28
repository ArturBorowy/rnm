package pl.arturborowy.rnm.data.remote

interface Mapper<FromT, ResultT> {

    fun map(from: FromT) : ResultT
}