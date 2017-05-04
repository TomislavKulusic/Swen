import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;


/**
 * Created by Tomislav on 5/4/2017.
 */
public class AlbumList extends JFrame implements ActionListener{

    private ArrayList<File> files;
    private Connection connection;

    public AlbumList() {
        createFrame();
    }

    public void createFrame() {
        setSize(500, 500);
        makeOptions();

        setVisible(true);
    }

    public void makeOptions() {
        JButton openAlbum = new JButton("Open Album");
        openAlbum.addActionListener(this);
        JPanel panel = new JPanel();
        panel.add(openAlbum);
        add(panel);
    }


    public void chooseAlbum() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        File workingDirectory = new File(System.getProperty("user.dir"));
        jfc.setCurrentDirectory(workingDirectory);
        int returnVal = jfc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            Loader loader = new Loader(jfc.getSelectedFile().getAbsolutePath());
            files = loader.getFiles();
        }

        this.dispose();
        ImageView iv = new ImageView(files);
        iv.getConnection();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Open Album")) {
            chooseAlbum();
        }
    }

    public void getAlbums() {

    }

    public static void main(String[] args) {
        new AlbumList();
    }
}
