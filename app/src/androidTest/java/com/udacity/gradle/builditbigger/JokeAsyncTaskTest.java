package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.util.Pair;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by inbkumar01 on 9/5/2017.
 */


public class JokeAsyncTaskTest extends AndroidTestCase {

    @SuppressWarnings("unchecked")
    public void test(){

        String result = null;
        try{
            result = new JokeAsyncTask().execute(new Pair<Context, String>(getContext(), "sample")).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        assertNotNull(result);

    }
}