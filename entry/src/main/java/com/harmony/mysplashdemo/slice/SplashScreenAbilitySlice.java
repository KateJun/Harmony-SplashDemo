package com.harmony.mysplashdemo.slice;

import com.harmony.mysplashdemo.ResourceTable;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Text;
import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;
import ohos.eventhandler.InnerEvent;

/**
 * Splash screen ability slice
 */
public class SplashScreenAbilitySlice extends AbilitySlice {
    private static final int COUNT_DOWN_EVENT = 1;
    private static final int COUNT_DOWN_TIME = 5;
    private static final int COUNT_DOWN_PERIOD = 1000;

    private Text skipButton;
    private Intent redirectIntent;
    private CountDownHandler handler;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_splash_screen_layout);
        initRedirectIntent();
        initRedirection();
    }

    @Override
    public void onActive() {
        super.onActive();
        handler.sendEvent(COUNT_DOWN_EVENT, 0);
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    protected void onBackground() {
        super.onBackground();
        if (handler != null) {
            handler.removeAllEvent();
        }
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if (handler != null) {
            handler.removeAllEvent();
        }
    }

    private void initRedirectIntent() {
        redirectIntent = new Intent();

        // Set redirect destiny ability
        Operation operation = new Intent.OperationBuilder().withDeviceId("")
                .withBundleName(getBundleName())
                .withAbilityName("com.harmony.mysplashdemo.MainAbility")
                .build();
        redirectIntent.setOperation(operation);
    }

    private void initRedirection() {
        // Set skip button click listener
        skipButton = (Text) findComponentById(ResourceTable.Id_skip_button_text);
        if (skipButton != null) {
            skipButton.setClickedListener(component -> {
                startAbility(redirectIntent);
                if (handler != null) {
                    handler.removeAllEvent();
                }
            });
        }

        // Set up count down event handler
        handler = new CountDownHandler(EventRunner.current());
    }

    /**
     * Countdown handler
     */
    private class CountDownHandler extends EventHandler {
        private int countDown = COUNT_DOWN_TIME;

        CountDownHandler(EventRunner runner) {
            super(runner);
        }

        @Override
        protected void processEvent(InnerEvent event) {
            super.processEvent(event);
            switch (event.eventId) {
                case COUNT_DOWN_EVENT:
                    skipButton.setText("skip " + countDown);
                    countDown--;
                    if (countDown >= 0) {
                        handler.sendEvent(COUNT_DOWN_EVENT, COUNT_DOWN_PERIOD);
                    } else {
                        startAbility(redirectIntent);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}