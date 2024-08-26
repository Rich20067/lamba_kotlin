package org.example

fun main(){
    val funciontrick = trick
    val puntos:(Int)->String={
        "$it cuartos"
    }

    val trickfunction=trucootrato(false,{"$it cuartos"})
    val treatfunction=trucootrato(true,null)

    repeat(4) {
        treatfunction()
    }
    trickfunction()
}

fun trucootrato(estruco:Boolean,trucoextra:((Int)->String)?): () -> Unit{
    if(estruco) {
        return trick
    }else{
        if(trucoextra!=null)
            println(trucoextra(3))
    }
    return treat
}

val trick = {
    println("¡Sin golosinas!")
}

val treat:()->Unit={
    println("Date un capricho")
}

val pastelsito:(Int)->String={
    "tengo un pastelsito"
}