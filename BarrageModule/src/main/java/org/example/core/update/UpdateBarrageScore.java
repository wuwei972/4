package org.example.core.update;

import org.example.constpool.BarrageModuleConstPool;
import org.example.core.listen.BarrageFileMonitor;
import org.example.pojo.Anchor;
import org.example.util.JsonFileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description
 * @Author welsir
 * @Date 2023/8/2 23:57
 */
public class UpdateBarrageScore {

    //Batch Edit
    public static void updateBarrageScore(List<Anchor> anchorList){

        for (Anchor oneAnchor : anchorList) {
            File anchorFiles = new File(BarrageModuleConstPool.ANCHOR_FILE_PATH);
            String fileName = oneAnchor.getPlathForm()+"-"+oneAnchor.getRoomId();
            for(File anchorFile : Objects.requireNonNull(anchorFiles.listFiles())){
                //Whether to add
                if(fileName.equals(anchorFile.getName())){
                    Map anchorBarrageMap = BarrageFileMonitor.getAnchorBarrageMap();
                    List<Anchor.property> originList = (List<Anchor.property>) anchorBarrageMap.get(fileName);
                    List<Anchor.property> newList = oneAnchor.getProperty();
                    newList = Stream.concat(originList.stream(),oneAnchor.getProperty().stream())
                            .distinct().collect(Collectors.toList());
                    JsonFileUtil.writeJsonFileIsExist(BarrageModuleConstPool.ANCHOR_FILE_PATH+fileName+BarrageModuleConstPool.AnchorFileType,oneAnchor);
                    break;
                }
            }
            JsonFileUtil.writeJsonFile(BarrageModuleConstPool.ANCHOR_FILE_PATH,fileName+BarrageModuleConstPool.AnchorFileType,oneAnchor);
        }
//        BarrageFileMonitor.sendHotEvent("updateAnchor");
    }
    public static void main(String[] args) {
        Anchor anchor = new Anchor();
        anchor.setNickName("姿态");
        anchor.setPlathForm("虎牙");
        anchor.setRoomId("123456");
        ArrayList<Anchor.property> arrayList = new ArrayList<>();
        Anchor.property property = new Anchor.property("hahaha",10);
        arrayList.add(property);
        anchor.setProperty(arrayList);
        updateBarrageScore(List.of(anchor));
    }
}