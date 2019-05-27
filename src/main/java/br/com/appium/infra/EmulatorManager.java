//package br.com.appium.infra;
//
//import br.com.appium.exceptions.ConfigException;
//import com.android.prefs.AndroidLocation;
//import com.android.repository.api.ConsoleProgressIndicator;
//import com.android.sdklib.ISystemImage;
//import com.android.sdklib.internal.avd.AvdInfo;
//import com.android.sdklib.internal.avd.AvdManager;
//import com.android.sdklib.repository.AndroidSdkHandler;
//import com.android.sdklib.repository.targets.SystemImage;
//import com.android.utils.ILogger;
//import com.android.utils.StdLogger;
//
//import java.io.File;
//import java.nio.file.Paths;
//import java.util.*;
//
//public class EmulatorManager {
//
//    private ILogger logger;
//
//    private AndroidSdkHandler sdkHandler;
//
//    private AvdManager avdManager;
//
//    private ConsoleProgressIndicator progressIndicator;
//
//    private File androidHome;
//
//    public EmulatorManager() throws ConfigException {
//
//        this.logger = new StdLogger(StdLogger.Level.WARNING);
//        this.progressIndicator = new ConsoleProgressIndicaodltor();
//
//        String androidHomeEnv = System.getenv("ANDROID_SDK_HOME");
//        if( androidHomeEnv == null ){
//            androidHomeEnv = System.getenv("ANDROID_HOME");
//        }
//
//        if(androidHomeEnv == null){
//            throw new ConfigException("A variável de ambiente ANDROID_HOME ou ANDROID_SDK_HOME não está definida. " +
//                    "É necessário defini-las para que seja possível gerenciar os AVDs");
//        }
//
//        this.androidHome = new File(androidHomeEnv);
//        if(!this.androidHome.exists()){
//            throw new ConfigException(String.format("O caminho %s usado como ANDROID_HOME, não é válido!", androidHome.toString()));
//        }
//
//        this.sdkHandler = AndroidSdkHandler.getInstance(this.androidHome);
//
//        try {
//            this.avdManager = AvdManager.getInstance(this.sdkHandler,this.logger);
//        } catch (AndroidLocation.AndroidLocationException e) {
//            throw new ConfigException(
//                    String.format("O caminho %s não está apontando para o diretório de instalação do android.", this.androidHome.toString()));
//        }
//
//    }
//
//
//    /**
//     *
//     * @return
//     */
//    public AvdInfo criarAvd() throws ConfigException {
//
//        File avdFolder = Paths.get(this.androidHome.toString(), ".android", "avd").toFile();
//
//
//        AvdInfo avdInfo = avdManager.getAvd("AutomaticAppium", false);
//
//        if(avdInfo == null){
//            ISystemImage image = getSystem(androidVersionApiLevelMap().get("7.1"));
//            avdInfo = avdManager.createAvd(avdFolder, "avdProgramatico", image, null, null, null,
//                    null, null, false, true, true, false, this.logger);
//        }
//
//        return avdInfo;
//    }
//
//
//    /**
//     *
//     * @return
//     */
//    private Map<String, Integer> androidVersionApiLevelMap(){
//
//        Map<String, Integer> apiLevel = new HashMap<>();
//        apiLevel.put("9", 28);
//        apiLevel.put("8.1.0", 27);
//        apiLevel.put("8.0.0", 26);
//        apiLevel.put("7.1", 25);
//        apiLevel.put("7.0", 24);
//        apiLevel.put("6.0", 23);
//        apiLevel.put("5.1", 22);
//        apiLevel.put("5.0", 21);
//        apiLevel.put("4.4", 19);
//        apiLevel.put("4.4.4", 19);
//
//        return apiLevel;
//    }
//
//    private SystemImage getSystem(Integer apiLevel) throws ConfigException {
//
//        Collection<SystemImage> images = sdkHandler.getSystemImageManager(progressIndicator).getImages();
//
//        return images.stream()
//                .filter((SystemImage image) -> image.getAndroidVersion().getApiLevel() == apiLevel)
//                .findFirst()
//                .orElseThrow(()->new ConfigException(String.format("Nenhuma imagem com apiLevel %s tente outra.",apiLevel)));
//
//    }
//
//}
