package com.daud.gamelevenecommerce.Util

import com.daud.gamelevenecommerce.Model.CartModel
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

    fun getFlashD(): MutableList<FlashDealsModel>?{
        val flashDList: MutableList<FlashDealsModel> = ArrayList()
        flashDList.add(FlashDealsModel(R.drawable.one,"550.00","#D81D4C"))
        flashDList.add(FlashDealsModel(R.drawable.two,"230.00","#68C037"))
        flashDList.add(FlashDealsModel(R.drawable.three,"1230.00","#094D82"))
        flashDList.add(FlashDealsModel(R.drawable.four,"560.00","#2B2B2B"))
        flashDList.add(FlashDealsModel(R.drawable.one,"550.00","#D81D4C"))
        flashDList.add(FlashDealsModel(R.drawable.two,"230.00","#68C037"))
        flashDList.add(FlashDealsModel(R.drawable.three,"1230.00","#094D82"))
        flashDList.add(FlashDealsModel(R.drawable.four,"560.00","#2B2B2B"))
        return flashDList
    }

    fun getDailyF(): MutableList<DailyFlashModel>?{
        val dailyFList: MutableList<DailyFlashModel> = ArrayList()
        dailyFList.add(DailyFlashModel(R.drawable.one,"550.00"))
        dailyFList.add(DailyFlashModel(R.drawable.two,"230.00"))
        dailyFList.add(DailyFlashModel(R.drawable.three,"1230.00"))
        dailyFList.add(DailyFlashModel(R.drawable.four,"560.00"))
        dailyFList.add(DailyFlashModel(R.drawable.one,"550.00"))
        dailyFList.add(DailyFlashModel(R.drawable.two,"230.00"))
        dailyFList.add(DailyFlashModel(R.drawable.three,"1230.00"))
        dailyFList.add(DailyFlashModel(R.drawable.four,"560.00"))
        return dailyFList
    }

    fun getHotC(): MutableList<HotCateModel>?{
        val hotCList: MutableList<HotCateModel> = ArrayList()
        hotCList.add(HotCateModel(R.drawable.one,"550.00","#D81D4C"))
        hotCList.add(HotCateModel(R.drawable.two,"230.00","#68C037"))
        hotCList.add(HotCateModel(R.drawable.three,"1230.00","#094D82"))
        hotCList.add(HotCateModel(R.drawable.four,"560.00","#2B2B2B"))
        hotCList.add(HotCateModel(R.drawable.one,"550.00","#D81D4C"))
        hotCList.add(HotCateModel(R.drawable.two,"230.00","#68C037"))
        hotCList.add(HotCateModel(R.drawable.three,"1230.00","#094D82"))
        hotCList.add(HotCateModel(R.drawable.four,"560.00","#2B2B2B"))
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
        ciList.add(CateItemModel(R.drawable.one,"Electronic\nDevice",1))
        ciList.add(CateItemModel(R.drawable.two,"Furnitures\nDevice",2))
        ciList.add(CateItemModel(R.drawable.three,"Casual\nBag",1))
        ciList.add(CateItemModel(R.drawable.four,"Electronic\nDevice",2))
        ciList.add(CateItemModel(R.drawable.one,"Electronic\nDevice",1))
        ciList.add(CateItemModel(R.drawable.two,"Furnitures\nDevice",2))
        ciList.add(CateItemModel(R.drawable.three,"Casual\nBag",1))
        ciList.add(CateItemModel(R.drawable.four,"Electronic\nDevice",2))
        return ciList
    }

    fun getCateList1(): Array<String>?{
        val list1 = arrayOf("Merchandise", "Retro Gaming Consoles", "Pre Owned (Badel)", "Xbox", "PlayStation 4", "Gaming Setup", "Tv & Audio")
        return list1
    }

    fun getCateList2(): Array<String>?{
        val list2 = arrayOf("Xbox", "PlayStation 4", "Gaming Setup", "Tv & Audio", "Merchandise", "Retro Gaming Consoles", "Pre Owned (Badel)")
        return list2
    }

    fun getCart(): MutableList<CartModel>?{
        var list: MutableList<CartModel> = ArrayList()
        list.add(CartModel(R.drawable.one,"Power Bank Water Gold","Sound Box","4500.00XAF","550.00 SAR",1))
        list.add(CartModel(R.drawable.two,"Power Bank Water Gold","Sound Box","4500.00XAF","230.00 SAR",2))
        list.add(CartModel(R.drawable.three,"Power Bank Water Gold","Sound Box","4500.00XAF","1230.00 SAR",3))
        list.add(CartModel(R.drawable.four,"Power Bank Water Gold","Sound Box","4500.00XAF","1230.00 SAR",4))
        list.add(CartModel(R.drawable.one,"Power Bank Water Gold","Sound Box","4500.00XAF","550.00 SAR",4))
        list.add(CartModel(R.drawable.two,"Power Bank Water Gold","Sound Box","4500.00XAF","230.00 SAR",3))
        list.add(CartModel(R.drawable.three,"Power Bank Water Gold","Sound Box","4500.00XAF","1230.00 SAR",2))
        list.add(CartModel(R.drawable.four,"Power Bank Water Gold","Sound Box","4500.00XAF","1230.00 SAR",1))
        return list
    }

    fun getWishlist(): MutableList<WishlistModel>?{
        var list: MutableList<WishlistModel> = ArrayList()
        list.add(WishlistModel(R.drawable.one,"Power Bank Water Gold","Sound Box","550.00 SAR"))
        list.add(WishlistModel(R.drawable.two,"Power Bank Water Gold","Sound Box","230.00 SAR"))
        list.add(WishlistModel(R.drawable.three,"Power Bank Water Gold","Sound Box","1230.00 SAR"))
        list.add(WishlistModel(R.drawable.four,"Power Bank Water Gold","Sound Box","1230.00 SAR"))
        list.add(WishlistModel(R.drawable.one,"Power Bank Water Gold","Sound Box","550.00 SAR"))
        list.add(WishlistModel(R.drawable.two,"Power Bank Water Gold","Sound Box","230.00 SAR"))
        list.add(WishlistModel(R.drawable.three,"Power Bank Water Gold","Sound Box","1230.00 SAR"))
        list.add(WishlistModel(R.drawable.four,"Power Bank Water Gold","Sound Box","1230.00 SAR"))
        return list
    }
}
