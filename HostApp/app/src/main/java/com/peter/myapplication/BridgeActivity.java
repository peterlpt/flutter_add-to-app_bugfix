package com.peter.myapplication;

import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

/**flu
 * @author peter
 */
public class BridgeActivity extends FlutterActivity {
    private static final String CHANNEL_NAME = "flutter.bridge.call_platform";

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        getFlutterEngine();
        //config a method channel
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL_NAME)
                .setMethodCallHandler((call, result) -> {
                    switch (call.method) {
                        case "testMethod":
                            result.success("This is respond result from Android native method");
                            break;
                        default:
                            result.notImplemented();
                            break;
                    }
                });
    }

    public static FlutterActivity.NewEngineIntentBuilder withNewEngine() {
        return new MyNewEngineIntentBuilder(BridgeActivity.class);
    }

    public static FlutterActivity.CachedEngineIntentBuilder withCachedEngine(@NonNull String cachedEngineId) {
        return new MyCachedEngineIntentBuilder(BridgeActivity.class, cachedEngineId);
    }

    /**
     * 之所以"空"继承{@link io.flutter.embedding.android.FlutterActivity.NewEngineIntentBuilder}，因为内建在Intent创建时静态的写定了
     * FlutterActivity.class，从而导致在此继承的子类中重写的configureFlutterEngine(FlutterEngine)不生效。
     * 且用到的NewEngineIntentBuilder的构造器包外不可访问，因此无法重写相关方法而继续保持按内建方式获取Intent。继承一下将构造器外用
     */
    public static class MyNewEngineIntentBuilder extends NewEngineIntentBuilder {

        MyNewEngineIntentBuilder(@NonNull Class<? extends FlutterActivity> activityClass) {
            super(activityClass);
        }
    }

    public static class MyCachedEngineIntentBuilder extends CachedEngineIntentBuilder{

        protected MyCachedEngineIntentBuilder(@NonNull Class<? extends FlutterActivity> activityClass, @NonNull String engineId) {
            super(activityClass, engineId);
        }
    }
}
