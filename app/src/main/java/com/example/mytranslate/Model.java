package com.example.mytranslate;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Model {
    @SerializedName("def")
    public String[] def;
    @SerializedName("tr")
    public String[] tr;
    @SerializedName("syn")
    public String[] syn;
    @SerializedName("mean")
    public String[] mean;
    @SerializedName("ex")
    public String[] ex;
    public String[] getDef() {
        return def;
    }
    public String[] getTr() {   return tr;  }
    public String[] getSyn() {
        return syn;
    }
    public String[] getMean() { return mean;   }
    public String[] getEx() {
        return ex;
    }

    public void setTr() {  this.tr=tr;  }
    public void  setSyn() {
        this.syn=syn;
    }
    public void  setMean() { this.mean=mean;   }
    public void  setEx() {
        this.ex=ex;
    }

}