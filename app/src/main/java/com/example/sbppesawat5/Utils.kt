package com.example.sbppesawat5

class Utils {
    companion object {
        fun getPlanes():MutableList<Plane> {
            val planes = mutableMapOf(
                1 to Plane(1, "Boeing 737", mutableListOf()),
                2 to Plane(2, "ATR 720", mutableListOf()),
                3 to Plane(3, "Apache", mutableListOf()),
                4 to Plane(4, "Eurocopter HH-65 Dolphin", mutableListOf()),
                5 to Plane(5, "Yakovlev Yak-141", mutableListOf()),
                6 to Plane(6, "Tupolev Tu-160", mutableListOf())
            )

            return planes.values.toMutableList()
        }

        fun getCharacteristics(): MutableList<Characteristic> {

            val characteristic = mutableMapOf(
                0 to Characteristic(1, "Apakah mengangkut penumpang ?",false),
                1 to Characteristic(2, "Apakah terbang membawa senjata ?",false),
                2 to Characteristic(3, "Apakah terbang cepat ?",false),
                3 to Characteristic(4, "Apakah mengangkut barang ?",false),
                4 to Characteristic(5, "Apakah tersedia di pangkalan udara ?",false),
                5 to Characteristic(6, "Apakah dapat terbang ?",false),
                6 to Characteristic(7, "Apakah tersedia di bandara ?",false),
                7 to Characteristic(8, "Apakah mempunyai pramugari ?",false),
                8 to Characteristic(9, "Apakah memiliki baling-baling atas ?",false),
                9 to Characteristic(10, "Apakah terbang rendah ?",false),
                10 to Characteristic(11, "Apakah buatan US ?",false),
                11 to Characteristic(12, "Apakah bermesin turbo fan ?",false),
                12 to Characteristic(13, "Apakah buatan eropa ?",false),
                13 to Characteristic(14, "Apakah propeller ?",false),
                14 to Characteristic(15, "Apakah helikopter serang ?",false),
                15 to Characteristic(16, "Apakah helikopter penyelamatan ?",false),
                16 to Characteristic(17, "Apakah dapat mendarat vertikal ?",false),
                17 to Characteristic(18, "Apakah buatan Rusia ?",false),
                18 to Characteristic(19, "Apakah penumpang tunggal ?",false),
                19 to Characteristic(20, "Apakah pengebom ?",false),
                20 to Characteristic(21, "Apakah supersonic ?",false)
            )

            return characteristic.values.toMutableList()
        }
    }
}