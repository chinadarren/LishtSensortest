package com.example.cl.lishtsensortest;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends Activity {

    private SensorManager sensorManager;
    private TextView lightLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lightLevel = (TextView) findViewById(R.id.light_level);
        // SensorManager的实例
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //调用 getDefaultSensor() 方法来得到任意的传感器类型
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
       // 调用 SensorManager的 registerListener()方法来注册 SensorEventListener 才能使其生效
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
//当程序退出或传感器使用完毕时，一定要调用 unregisterListener ()方 法将使用的资源释放掉
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null) {
            sensorManager.unregisterListener(listener);
        }
    }

    //对传感器输出的信号进行监听，借助 SensorEventListener来实现
    //当光照强度发生变化的时候就会调用 SensorEventListener 的 onSensorChanged()方法
    private SensorEventListener listener = new SensorEventListener() {
        //当传感器监测到的数 值发生变化时会调用 onSensorChanged()方法
        @Override
        public void onSensorChanged(SensorEvent event) {
            //values数组中第一个下标的值就是当前的光照强度
            float value = event.values[0];
            lightLevel.setText("Current light level is " + value + "1x");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
