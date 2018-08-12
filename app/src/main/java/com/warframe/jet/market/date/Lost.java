package com.warframe.jet.market.date;

import cn.bmob.v3.BmobObject;

/**
 * Created by jet on 2018-08-11.
 */

public class Lost extends BmobObject
{
    private String title;//交易物品
    private String describe;//交易详情
    private String phone;//用户名

//   public Lost(String title,String describe,String phone){
//       this.title = title;
//       this.describe = describe;
//       this.phone = phone;
//    }

    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getDescribe()
    {
        return describe;
    }
    public void setDescribe(String describe)
    {
        this.describe = describe;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

}
