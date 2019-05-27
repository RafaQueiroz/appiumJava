package br.com.appium.infra;

import com.android.repository.api.ConsoleProgressIndicator;
import com.android.sdklib.IAndroidTarget;
import com.android.sdklib.ISystemImage;
import com.android.sdklib.internal.avd.AvdManager;
import com.android.sdklib.repository.AndroidSdkHandler;
import com.android.utils.ILogger;
import com.android.utils.StdLogger;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EmuladorManager {

    ILogger logger = new StdLogger(StdLogger.Level.VERBOSE);

    AndroidSdkHandler sdkHandler = AndroidSdkHandler.getInstance(Paths.get(System.getenv("ANDROID_HOME")).toFile());
    AvdManager avdManager = AvdManager.getInstance(sdkHandler,logger);


    File avdFolder = Paths.get(System.getenv("HOME"), ".android", "avd").toFile();

    ConsoleProgressIndicator progressIndicator = new ConsoleProgressIndicator();
    List<IAndroidTarget> targets = new ArrayList<IAndroidTarget>(sdkHandler.getAndroidTargetManager(progressIndicator).getTargets(progressIndicator));

    List<ISystemImage> images = new ArrayList<>(sdkHandler.getSystemImageManager(progressIndicator).getImages());
    ISystemImage image = images.get(0);
        avdManager.createAvd(avdFolder, "avdProgramatico", image, null, null, null, null, null, false, true, true, false, logger);

}
