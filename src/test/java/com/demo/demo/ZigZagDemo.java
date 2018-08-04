package com.demo.demo;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 *
 * @author:
 * @date: 2018-08-04 上午12:21
 **/
public class ZigZagDemo {

    @Test
    public void test(){
//        Scanner in = new Scanner(System.in);
        byte[] arr = varintEncode(4777);
        for(byte data : arr){
            System.err.printf("%x\t", data);
        }
        System.err.print(vaintDecode(arr));
        long val = zigZagEncode(-15);
        System.err.println("zigZag encode: "+val);
        long result = zigZagDecode(val);
        System.err.println("zigZag decode: "+result);
    }

    private long zigZagEncode(long val){
        long result =0L;
        if(val>=0){
            result = val<<1;
        }else{
            result = ((-val)<<1)-1;
        }
        return result;
    }

    private long zigZagDecode(long val){
        long result=0L;
        if(val%2==0){
            result =val>>1;
        }else{
            result = -((val+1)>>1);
        }
        return result;
    }

    private byte[] varintEncode(long val){
        List<Byte> stack = new LinkedList<Byte>();
        byte temp;
        int index=0;
        byte[] result=null;
        while(val>0){
            temp = (byte)(val&0x7f);
            val>>=7;
            if(index++ >0){
                temp |=0x80;
            }
            ((LinkedList<Byte>) stack).push(temp);
        }
        if(!stack.isEmpty()) {
            int i=0;
            result = new byte[stack.size()];
            while(!stack.isEmpty()){
                result[i++] =((LinkedList<Byte>) stack).pop();
            }
        }
        return result;
    }

    private long vaintDecode(byte[] arr){
        long result=0;
        if(null ==arr && arr.length==0){
            return 0;
        }
        int len = arr.length;
        for(int i = len-1; i>=0; i--){
            result+=(arr[i]&0x7f)<<((len-1-i)*7);
        }
        return result;
    }
}
