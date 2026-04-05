package is.vidmot.switcher;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 *
 */
public enum View {
    BORD("/is/vidmot/Ludo-view.fxml"),
    VALMYND("/is/vidmot/Valmynd-view.fxml");


    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
