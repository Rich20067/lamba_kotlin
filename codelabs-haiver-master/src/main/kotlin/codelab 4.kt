package org.example

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class Smart(val name: String, val category: String) {
    var deviceStatus = "conectado"
        protected set(value) {
            field = value
        }
    open val devicetype="unknown"

    open fun encendido() {
        deviceStatus="on"
    }
    open fun apagado() {
        deviceStatus="off"
    }
}
class SmartTvDevice(deviceName: String, deviceCategory: String) :
    Smart(name = deviceName, category = deviceCategory) {

    override val devicetype="smart tv"

    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

    fun subirvolumen(){
        speakerVolume++
        println("se subio el volumen a: $speakerVolume")
    }
    fun cambiarcanal(){
        channelNumber++
        println("se cambio el canal a: $channelNumber")
    }
    override fun encendido() {
        super.encendido()
        println("$name está prendido. El volumen del altavoz está configurado en: $speakerVolume " +
                "y el número de canal es: $channelNumber")
    }
    override fun apagado() {
        super.apagado()
        println("$name turned off")
    }
}

class SmartLightDevice(deviceName: String, deviceCategory: String) :
    Smart(name = deviceName, category = deviceCategory) {
    override val devicetype=" luz inteligente"
    private var nivelbrillo by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)

    fun increaseBrightness() {
        nivelbrillo++
        println("el brillo se subio a: $nivelbrillo")
    }
    override fun encendido() {
        super.encendido()
        nivelbrillo = 50
        println("$name encendido, el nivel de brillo es de: $nivelbrillo")
    }

    override fun apagado() {
        super.apagado()
        nivelbrillo = 0
        println("Luz inteligente apagada")
    }
}

class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice
) {
    var deviceTurnOnCount = 0
        private set

    fun turnOnTv() {
        deviceTurnOnCount++
        smartTvDevice.encendido()
    }
    fun turnOffTv() {
        deviceTurnOnCount--
        smartTvDevice.apagado()
    }
    fun increaseTvVolume() {
        smartTvDevice.subirvolumen()
    }

    fun changeTvChannelToNext() {
        smartTvDevice.cambiarcanal()
    }
    fun turnOnLight() {
        deviceTurnOnCount++
        smartLightDevice.encendido()
    }
    fun turnOffLight() {
        deviceTurnOnCount--
        smartLightDevice.apagado()
    }
    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }
    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

class RangeRegulator(initialValue: Int,
                     private val minValue: Int,
                     private val maxValue: Int) : ReadWriteProperty<Any?, Int> {
    var fieldData = initialValue
    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
}
fun main() {
    var smartDevice:Smart = SmartTvDevice("LG","entretenimiento")
    smartDevice.encendido()

    smartDevice = SmartLightDevice("Google Light", "Utility")
    smartDevice.encendido()
}
