package com.daud.gamelevenecommerce.Util

import com.daud.gamelevenecommerce.Model.*
import com.daud.gamelevenecommerce.R
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

class Data {

    fun homeSlider(): MutableList<SlideModel>? {
        val imgList: MutableList<SlideModel> = ArrayList()
        imgList.add(SlideModel(R.drawable.slider_xd, ScaleTypes.FIT))
        imgList.add(SlideModel(R.drawable.slider_one, ScaleTypes.FIT))
        imgList.add(SlideModel(R.drawable.slider_two, ScaleTypes.FIT))
        imgList.add(SlideModel(R.drawable.slider_three, ScaleTypes.FIT))
        return imgList
    }

    fun getText(): Array<String>{
        val textList = arrayOf("Hot", "Computer & Office", "Phone Accessories", "Gaming PC", "Toys")
        return textList
    }

    fun getFlashD(): MutableList<FlashDModel>?{
        val flashDList: MutableList<FlashDModel> = ArrayList()
        flashDList.add(FlashDModel(R.drawable.one,"550.00","#D81D4C"))
        flashDList.add(FlashDModel(R.drawable.two,"230.00","#68C037"))
        flashDList.add(FlashDModel(R.drawable.three,"1230.00","#094D82"))
        flashDList.add(FlashDModel(R.drawable.four,"560.00","#2B2B2B"))
        flashDList.add(FlashDModel(R.drawable.one,"550.00","#D81D4C"))
        flashDList.add(FlashDModel(R.drawable.two,"230.00","#68C037"))
        flashDList.add(FlashDModel(R.drawable.three,"1230.00","#094D82"))
        flashDList.add(FlashDModel(R.drawable.four,"560.00","#2B2B2B"))
        return flashDList
    }

    fun getDailyF(): MutableList<DailyFModel>?{
        val dailyFList: MutableList<DailyFModel> = ArrayList()
        dailyFList.add(DailyFModel(R.drawable.one,"550.00"))
        dailyFList.add(DailyFModel(R.drawable.two,"230.00"))
        dailyFList.add(DailyFModel(R.drawable.three,"1230.00"))
        dailyFList.add(DailyFModel(R.drawable.four,"560.00"))
        dailyFList.add(DailyFModel(R.drawable.one,"550.00"))
        dailyFList.add(DailyFModel(R.drawable.two,"230.00"))
        dailyFList.add(DailyFModel(R.drawable.three,"1230.00"))
        dailyFList.add(DailyFModel(R.drawable.four,"560.00"))
        return dailyFList
    }

    fun getHotC(): MutableList<HotCModel>?{
        val hotCList: MutableList<HotCModel> = ArrayList()
        hotCList.add(HotCModel(R.drawable.one,"550.00","#D81D4C"))
        hotCList.add(HotCModel(R.drawable.two,"230.00","#68C037"))
        hotCList.add(HotCModel(R.drawable.three,"1230.00","#094D82"))
        hotCList.add(HotCModel(R.drawable.four,"560.00","#2B2B2B"))
        hotCList.add(HotCModel(R.drawable.one,"550.00","#D81D4C"))
        hotCList.add(HotCModel(R.drawable.two,"230.00","#68C037"))
        hotCList.add(HotCModel(R.drawable.three,"1230.00","#094D82"))
        hotCList.add(HotCModel(R.drawable.four,"560.00","#2B2B2B"))
        return hotCList
    }

    fun getBrand(): MutableList<BrandsModel>?{
        val brandsList: MutableList<BrandsModel> = ArrayList()
        brandsList.add(BrandsModel(R.drawable.one,R.drawable.diesel))
        brandsList.add(BrandsModel(R.drawable.two,R.drawable.gionee))
        brandsList.add(BrandsModel(R.drawable.three,R.drawable.fedex))
        brandsList.add(BrandsModel(R.drawable.four,R.drawable.micromax))
        brandsList.add(BrandsModel(R.drawable.one,R.drawable.diesel))
        brandsList.add(BrandsModel(R.drawable.two,R.drawable.gionee))
        brandsList.add(BrandsModel(R.drawable.three,R.drawable.fedex))
        brandsList.add(BrandsModel(R.drawable.four,R.drawable.micromax))
        return brandsList
    }

    fun getAds(): MutableList<AdsModel>?{
        val adsList: MutableList<AdsModel> = ArrayList()
        adsList.add(AdsModel(R.drawable.one,"Power Bank Water Gold","Sound Box","500.00"))
        adsList.add(AdsModel(R.drawable.two,"Power Bank Water Gold","Sound Box","500.00"))
        adsList.add(AdsModel(R.drawable.three,"Power Bank Water Gold","Sound Box","500.00"))
        adsList.add(AdsModel(R.drawable.four,"Power Bank Water Gold","Sound Box","500.00"))
        adsList.add(AdsModel(R.drawable.one,"Power Bank Water Gold","Sound Box","500.00"))
        adsList.add(AdsModel(R.drawable.two,"Power Bank Water Gold","Sound Box","500.00"))
        adsList.add(AdsModel(R.drawable.three,"Power Bank Water Gold","Sound Box","500.00"))
        adsList.add(AdsModel(R.drawable.four,"Power Bank Water Gold","Sound Box","500.00"))
        return adsList
    }

    fun getCateItem(): MutableList<CateItemModel>?{
        val ciList: MutableList<CateItemModel> = ArrayList()
        ciList.add(CateItemModel(R.drawable.one,"Electronic\nDevice"))
        ciList.add(CateItemModel(R.drawable.two,"Furnitures\nDevice"))
        ciList.add(CateItemModel(R.drawable.three,"Casual\nBag"))
        ciList.add(CateItemModel(R.drawable.four,"Electronic\nDevice"))
        ciList.add(CateItemModel(R.drawable.one,"Electronic\nDevice"))
        ciList.add(CateItemModel(R.drawable.two,"Furnitures\nDevice"))
        ciList.add(CateItemModel(R.drawable.three,"Casual\nBag"))
        ciList.add(CateItemModel(R.drawable.four,"Electronic\nDevice"))
        return ciList
    }
}
