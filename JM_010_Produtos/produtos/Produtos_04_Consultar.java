
package produtos;

import Sons.Som;
import br.com.jmary.home.Home;
import br.com.jmary.home.imagens.Imagens_Internas;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import br.com.jmary.home.jpa.DAOGenericoJPA;
import br.com.jmary.home.jpa.DB;
import br.com.jmary.home.jpa.DB_Bean;
import br.com.jmary.home.jpa.JPAUtil;
import br.com.jmary.utilidades.Arquivo_Ou_Pasta;
import br.com.jmary.utilidades.Exportando;
import br.com.jmary.utilidades.ImportarExportarExcel;
import br.com.jmary.utilidades.JFileChooserJM;
import br.com.jmary.utilidades.JOPM;
import controle_de_acesso.Controle_De_Acesso_02_Cadastrar_Visualizar;
import controle_de_acesso.Verificar_Autorizacao;
import home_controle_menus_norte.imagens.Imagens_Menu_Norte;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JTabbedPane;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import produtos_03_cadastro_automatizado_ajuda.Produtos_03_Cadastro_Automatizado_Ajuda;
import produtos_beans.Produtos;
import usuarios_do_sistema_excel.Usuarios_Do_Sistema_Excel_Modelo;
import produtos_imprimir.Produtos_Imprimir_01_Controle;
import visualizador_imagens.Visualizador_Interno;
import visualizador_imagens_beans.UsuarioImagens;

/**
 *
 * @author AnaMariana
 */
public class Produtos_04_Consultar extends javax.swing.JPanel {   
    
    Som tocarSon = new Som();
    int resposta = 0;
    boolean respondeuCerto = false;
    JPanel PerguntaX = new JPanel();
    Tabela_JM TB;
;    
    Home Home;
    
    /** Creates new form SombraVendas
     * @param Home2 */
    public Produtos_04_Consultar( Home Home2 ) {
        initComponents();
        
        Home = Home2;
        TB = new Tabela_JM();
        setar_Tabela();
        
        PerguntaX = Pergunta;
        
        Home.ControleTabs.removerTabSemControleSelecionadoPeloNome(jTabbedPane1,Pergunta);
        inserirTab_Interna_Ajuda();
        
        setarUrl_e_ImageIcon_Seta_Inicio();
        
        selecionar_usuario = false;
        lbSelecionar.setVisible(false);
        lbVisualizar.setVisible(true);
        lbDesativar.setVisible(true);
        lbEditar.setVisible(true);
    }
    
    JTabbedPane jTabselecionar_usuario;
    boolean selecionar_usuario = false;
    Controle_De_Acesso_02_Cadastrar_Visualizar Controle_De_Acesso_02_Cadastrar_Visualizar;
    public Produtos_04_Consultar( Home Home2, JTabbedPane jTabselecionar_usuario2, Controle_De_Acesso_02_Cadastrar_Visualizar Controle_De_Acesso_02_Cadastrar_Visualizar2 ) {
        initComponents();
        
        Home = Home2;
        
        TB = new Tabela_JM();
        setar_Tabela();
        
        /*
        PerguntaX = Pergunta;
        
        Home.ControleTabs.removerTabSemControleSelecionadoPeloNome(jTabbedPane1,Pergunta);
        inserirTab_Interna_Ajuda();
        */
        
        setarUrl_e_ImageIcon_Seta_Inicio();
        
        jTabselecionar_usuario = jTabselecionar_usuario2;
        selecionar_usuario = true;
        Controle_De_Acesso_02_Cadastrar_Visualizar = Controle_De_Acesso_02_Cadastrar_Visualizar2;
        lbSelecionar.setVisible(true);
        lbVisualizar.setVisible(false);
        lbDesativar.setVisible(false);
        lbEditar.setVisible(false);
    }
    
    private void inserirTab_Interna_Ajuda(){
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
            int img_Atual = 1;
            int quantidade_de_arquivos_ajuda = 2;    //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////             
            
            JScrollPane sc = new JScrollPane();
            sc.setViewportView(new Visualizador_Interno(Home,img_Atual,quantidade_de_arquivos_ajuda,Produtos_03_Cadastro_Automatizado_Ajuda.class) );
            
            Home.ControleTabs.AddTabSemControleNovoSemThread(jTabbedPane1, "Ajuda", "ajuda.gif", sc);
            
            Thread.sleep( 5 ); 
            //Adicionando pergunta após a ajuda
            Home.ControleTabs.AddTabSemControleNovoSemThread(jTabbedPane1, "Consultar Usuário", "livroTp.gif", PerguntaX);
                        
        } catch( Exception e ){  } } }.start();                 
    }
    
    private void inserirTab_Externa_PPT(){
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
            String endereco_Externo_da_Pasta = System.getProperty("user.dir") + "\\00_Externo\\Cencosud\\Documentos\\"
                    + "Elenco\\Consultar\\IMG_PPT\\";  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////             
            int img_Atual = 0; 
            
            JScrollPane sc = new JScrollPane();
            //sc.setViewportView( new Visualizador_Externo(Home,img_Atual,endereco_Externo_da_Pasta) );
            
            Home.ControleTabs.AddTabsAoHome("PPT", "livroTp.gif", sc );
       
        } catch( Exception e ){  } } }.start();                 
    }
    
    private void setar_Tabela(){
        /*new Thread() {   @Override public void run() {*/ try { 
            
            jp_Tabela.setLayout( new BorderLayout() );
            jp_Tabela.add(TB , BorderLayout.CENTER );
            
        } catch( Exception e ){  } //} }.start();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Pergunta = new javax.swing.JPanel();
        jp_Tabela = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jp_Opcoes_Tabela2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jp_Opcoes_Tabela = new javax.swing.JPanel();
        jp_Opcoes = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        lb_Exportar_Exel = new javax.swing.JLabel();
        lb_Impressora = new javax.swing.JLabel();
        lbFiltro1 = new javax.swing.JLabel();
        lbFiltro2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbLinha_Tabela = new javax.swing.JLabel();
        lbColuna_Tabela = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel14 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        JPOpcao_5 = new javax.swing.JPanel();
        jTextArea5 = new javax.swing.JTextArea();
        jComboBox2 = new javax.swing.JComboBox();
        lbEditar = new javax.swing.JLabel();
        lbDesativar = new javax.swing.JLabel();
        lbVisualizar = new javax.swing.JLabel();
        lbSelecionar = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(832, 504));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1170, 1202));

        javax.swing.GroupLayout jp_TabelaLayout = new javax.swing.GroupLayout(jp_Tabela);
        jp_Tabela.setLayout(jp_TabelaLayout);
        jp_TabelaLayout.setHorizontalGroup(
            jp_TabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 807, Short.MAX_VALUE)
        );
        jp_TabelaLayout.setVerticalGroup(
            jp_TabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 205, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Produtos - Consulta");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/seta_para_cima.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jp_Opcoes.setBackground(new java.awt.Color(204, 204, 204));

        jPanel3.setBackground(new java.awt.Color(0, 153, 0));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(240, 240, 240));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("IMPORTAR");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(255, 0, 51));
        jPanel10.setPreferredSize(new java.awt.Dimension(130, 43));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(240, 240, 240));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("OPÇÕES");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(253, 254, 247));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/android_24.png"))); // NOI18N
        jLabel8.setToolTipText("IMPORTAR DA MEMÓRIA");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.setEnabled(false);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel8))
        );

        jPanel13.setBackground(new java.awt.Color(253, 254, 247));

        lb_Exportar_Exel.setForeground(new java.awt.Color(0, 102, 0));
        lb_Exportar_Exel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/xlsx.png"))); // NOI18N
        lb_Exportar_Exel.setToolTipText("EXPORTAR PARA EXCEL");
        lb_Exportar_Exel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_Exportar_Exel.setEnabled(false);
        lb_Exportar_Exel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lb_Exportar_ExelMousePressed(evt);
            }
        });

        lb_Impressora.setForeground(new java.awt.Color(0, 102, 0));
        lb_Impressora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/printv.png"))); // NOI18N
        lb_Impressora.setToolTipText("IMPRIMIR ");
        lb_Impressora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_Impressora.setEnabled(false);
        lb_Impressora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lb_ImpressoraMousePressed(evt);
            }
        });

        lbFiltro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/filtro2.png"))); // NOI18N
        lbFiltro1.setToolTipText("FILTRAR COLUNA - PELO ITEM SELECIONADO");
        lbFiltro1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbFiltro1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbFiltro1MousePressed(evt);
            }
        });

        lbFiltro2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/filtro2reverso.png"))); // NOI18N
        lbFiltro2.setToolTipText("EXCLUIR TODOS DA COLUNA = ITEM SELECIONADO");
        lbFiltro2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbFiltro2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbFiltro2MousePressed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/exluir.png"))); // NOI18N
        jLabel6.setToolTipText("EXCLUIR COLUNA SELECIONADA");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/zoom_out.png"))); // NOI18N
        jLabel9.setToolTipText("PROCURAR E EXCLUIR");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
        });

        lbLinha_Tabela.setForeground(new java.awt.Color(0, 102, 0));
        lbLinha_Tabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/tabela_horizontal.png"))); // NOI18N
        lbLinha_Tabela.setToolTipText("SELECIONAR HORIZONTALMENTE");
        lbLinha_Tabela.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbLinha_Tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbLinha_TabelaMousePressed(evt);
            }
        });

        lbColuna_Tabela.setForeground(new java.awt.Color(0, 102, 0));
        lbColuna_Tabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/tabela_vertical.png"))); // NOI18N
        lbColuna_Tabela.setToolTipText("SELECIONAR VERTICALMENTE");
        lbColuna_Tabela.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbColuna_Tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbColuna_TabelaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lb_Impressora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_Exportar_Exel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFiltro2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFiltro1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbColuna_Tabela)
                    .addComponent(lbLinha_Tabela)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFiltro1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLinha_Tabela, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFiltro2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbColuna_Tabela, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(lb_Exportar_Exel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_Impressora, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/xls.png"))); // NOI18N
        jLabel7.setToolTipText("IMPORTAR DO EXCEL");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.setEnabled(false);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel7MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jp_OpcoesLayout = new javax.swing.GroupLayout(jp_Opcoes);
        jp_Opcoes.setLayout(jp_OpcoesLayout);
        jp_OpcoesLayout.setHorizontalGroup(
            jp_OpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_OpcoesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jp_OpcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jp_OpcoesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jp_OpcoesLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jp_OpcoesLayout.setVerticalGroup(
            jp_OpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_OpcoesLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_OpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jp_OpcoesLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel10, jPanel3});

        javax.swing.GroupLayout jp_Opcoes_TabelaLayout = new javax.swing.GroupLayout(jp_Opcoes_Tabela);
        jp_Opcoes_Tabela.setLayout(jp_Opcoes_TabelaLayout);
        jp_Opcoes_TabelaLayout.setHorizontalGroup(
            jp_Opcoes_TabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_Opcoes_TabelaLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jp_Opcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jp_Opcoes_TabelaLayout.setVerticalGroup(
            jp_Opcoes_TabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_Opcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTextPane1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane4.setViewportView(jTextPane1);

        jPanel14.setBackground(new java.awt.Color(0, 0, 102));

        jLabel16.setFont(new java.awt.Font("Calibri", 0, 30)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Cole Aquî");
        jLabel16.setToolTipText("Cole o Texto ao Lado e Click em Importar Texto ");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel17.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/botaoDireitoHtmlPane.png"))); // NOI18N

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/jmary/home/imagens/livroTp.gif"))); // NOI18N
        jButton6.setText("Consultar");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton6MousePressed(evt);
            }
        });

        jPanel15.setBackground(new java.awt.Color(253, 254, 247));
        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/view_detail.png"))); // NOI18N
        jLabel18.setToolTipText("Pesquisa com Repetição - Passados e Futuros");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        JPOpcao_5.setBackground(new java.awt.Color(253, 254, 247));
        JPOpcao_5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(153, 0, 153)));
        JPOpcao_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_5MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_5MousePressed(evt);
            }
        });

        jTextArea5.setEditable(false);
        jTextArea5.setColumns(20);
        jTextArea5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextArea5.setRows(5);
        jTextArea5.setText("\n   Consultar com \n   SQL( ****%% )");
        jTextArea5.setToolTipText("Digite a Consulta SQL ao Lado");
        jTextArea5.setBorder(null);
        jTextArea5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTextArea5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextArea5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextArea5MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextArea5MousePressed(evt);
            }
        });

        javax.swing.GroupLayout JPOpcao_5Layout = new javax.swing.GroupLayout(JPOpcao_5);
        JPOpcao_5.setLayout(JPOpcao_5Layout);
        JPOpcao_5Layout.setHorizontalGroup(
            JPOpcao_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextArea5, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
        );
        JPOpcao_5Layout.setVerticalGroup(
            JPOpcao_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextArea5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addGap(0, 0, 0)
                .addComponent(JPOpcao_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPOpcao_5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DESCRIÇÃO", "CÓDIGO AUXILIAR", "ID", "SQL", "TUDO" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        lbEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/livroTp.png"))); // NOI18N
        lbEditar.setToolTipText("Editar");
        lbEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbEditar.setEnabled(false);
        lbEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbEditarMousePressed(evt);
            }
        });

        lbDesativar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/exluir.png"))); // NOI18N
        lbDesativar.setToolTipText("Excluir");
        lbDesativar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbDesativar.setEnabled(false);
        lbDesativar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbDesativarMousePressed(evt);
            }
        });

        lbVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/view_detail.png"))); // NOI18N
        lbVisualizar.setToolTipText("Visualizar");
        lbVisualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbVisualizar.setEnabled(false);
        lbVisualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbVisualizarMousePressed(evt);
            }
        });

        lbSelecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/conexao.png"))); // NOI18N
        lbSelecionar.setToolTipText("Visualizar");
        lbSelecionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbSelecionar.setEnabled(false);
        lbSelecionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbSelecionarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBox2, 0, 127, Short.MAX_VALUE))
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbDesativar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lbVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDesativar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jp_Opcoes_Tabela2Layout = new javax.swing.GroupLayout(jp_Opcoes_Tabela2);
        jp_Opcoes_Tabela2.setLayout(jp_Opcoes_Tabela2Layout);
        jp_Opcoes_Tabela2Layout.setHorizontalGroup(
            jp_Opcoes_Tabela2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_Opcoes_Tabela2Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jp_Opcoes_Tabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jp_Opcoes_Tabela2Layout.setVerticalGroup(
            jp_Opcoes_Tabela2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jp_Opcoes_Tabela, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout PerguntaLayout = new javax.swing.GroupLayout(Pergunta);
        Pergunta.setLayout(PerguntaLayout);
        PerguntaLayout.setHorizontalGroup(
            PerguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PerguntaLayout.createSequentialGroup()
                .addGroup(PerguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp_Tabela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PerguntaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PerguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jp_Opcoes_Tabela2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        PerguntaLayout.setVerticalGroup(
            PerguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PerguntaLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_Opcoes_Tabela2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jp_Tabela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        jTabbedPane1.addTab("Consulta Automática", Pergunta);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void abrir_Resposta(String icon){
        try { 
            Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
            JOptionPane pane = new JOptionPane( new ImageIcon( clazzHome.getResource(icon)) );
            final JDialog dialog = pane.createDialog( Home, "" ); 
            dialog.setModal(true);
            Timer timer = new Timer(2 * 1000, new ActionListener() {  
                @Override
                public void actionPerformed(ActionEvent ev) {  
                    dialog.dispose();   
                }  
            });  
            timer.setRepeats(false);  
            timer.start();  
            dialog.show();  
            timer.stop(); 
        } catch( Exception e ){ }
    }
        
    Exportando Exportando;                                            
    private void lb_Exportar_ExelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_Exportar_ExelMousePressed
        if( lb_Exportar_Exel.isEnabled() == true ){
        
        new Thread() { @Override public void run() { try{
        
        Object[] options = {
            "EXPORTAR PARA EXCEL",
            "CANCELAR" 
        };
        
        int n = JOptionPane.showOptionDialog(null,
            "Confirme a Exportação.",
            "Opção Exportação",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);        
        if(n==0){ 
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
        int numLinhas = -1; try{ numLinhas = TB.tbPesquisa.getRowCount();    } catch( Exception e ){}
        int numColuna = -1; try{ numColuna = TB.tbPesquisa.getColumnCount(); } catch( Exception e ){}
        if ( numLinhas != -1){
            try {         
                Arquivo_Ou_Pasta.criarPasta(System.getProperty("user.dir"), "Arquivos_Exportados");
                Thread.sleep( 1 );
                /////////////////////////////////////////////////////////////////////////////////////
                //String entrada = System.getProperty("user.dir") + "//"+ "Arquivos" + "//" +  
                Class<Usuarios_Do_Sistema_Excel_Modelo> clazz_Entrada_Excel = Usuarios_Do_Sistema_Excel_Modelo.class;
                ////////////////////////////////////////////////////////////////////////////////////
                String saida = System.getProperty("user.dir") + "//"+ "Arquivos_Exportados" + "//" + 
                        "usuarios_do_sistema_excel" + ".xlsx";  
                ///////////////////////////////////////////////////////////////////////////////////
                Arquivo_Ou_Pasta.deletar(new File(saida));
                Thread.sleep( 1 );
                            
                Exportando = new Exportando("EXPORTANDO DADOS...");
                Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                                                               
                //FileInputStream fileIn = null;
                FileOutputStream fileOut = null;                            
                            
                try{
                                
                    //fileIn = new FileInputStream( entrada );
                    //fileIn = new FileInputStream( clazzHome.getResourceAsStream("usuarios_do_sistema_excel_modelo.xlsx") );
                    //XSSFWorkbook wb = new XSSFWorkbook(fileIn); 
                    XSSFWorkbook wb = new XSSFWorkbook(clazz_Entrada_Excel.getResourceAsStream("usuarios_do_sistema_excel_modelo.xlsx")); 
                    XSSFSheet aba = wb.getSheetAt(0);   
                                
                    Exportando.pbg.setMaximum( numLinhas );
                                                                                    
                    XSSFCell cell;  
                    for (int i = -1; i < numLinhas; i++) { 
                        int linhaParaescrever = i+2;
                        XSSFRow linha = aba.getRow(linhaParaescrever);
                        //XSSFRow linha = aba.createRow(i+1);
                                    
                        for (int j = 0; j < numColuna ; j++) { 
                                        
                            cell = linha.getCell(j); 
                            if (cell == null) cell = linha.createCell(j);
                                        
                                try{ cell.setCellType(XSSFCell.CELL_TYPE_STRING); }catch(Exception e){}
                                try{ cell.setCellValue(XSSFCell.CELL_TYPE_STRING); }catch(Exception e){}
                                        
                                if(i==-1){
                                    cell.setCellValue(String.valueOf(TB.tbPesquisa.getColumnName(j)));
                                }else{
                                            
                                    cell.setCellValue(String.valueOf(TB.tbPesquisa.getValueAt(i, j)));
                                }
                        }
                        
                        Exportando.pbg.setValue( i );                                
                    }
                                                                
                    wb.setForceFormulaRecalculation(true);
                    fileOut = new FileOutputStream( saida );
                    wb.write(fileOut);
                                
                    try{
                        fileOut.close(); 
                        //fileIn.close(); 
                    } catch(Exception e) {}
                                    
                    java.awt.Desktop.getDesktop().open( new File( saida ) ); 
                } catch(Exception e) {}
                                                                    
                Exportando.fechar();                   
            }catch( Exception e ){ e.printStackTrace(); }
        }    
        else{
            
            Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
            //ImageIcon icon = new ImageIcon( clazzHome.getResource( imgURLIcon ) );
                    JOPM JOptionPaneMod = new JOPM( 1, "EXPORTAR PARA EXCEL\n"
                            + "\nSTATUS DA EXPORTAÇÃO:"
                            + "\nPARA EXPORTAR É NECESSÁRIO QUE A TABELA NÃO ESTEJA VAZIA!\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
        }
        }catch( Exception e ){ } } }.start();
        
        }
    }//GEN-LAST:event_lb_Exportar_ExelMousePressed

    private void lb_ImpressoraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_ImpressoraMousePressed
        if( lb_Impressora.isEnabled() == true ){
        
        new Thread() { @Override public void run() { try{
        
        Object[] options = {
            "IMPRIMIR",
            "CANCELAR" 
        };
        
        int n = JOptionPane.showOptionDialog(null,
            "Confirme a Impressão.",
            "Opção de Imprimir",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);        
        if(n==0){ 
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
            Exportando = new Exportando("IMPRIMINDO...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 ); 

            Produtos_Imprimir_01_Controle t1 = new Produtos_Imprimir_01_Controle( TB.tbPesquisa );
            t1.setName("ControleThread_Print");
            t1.start();
            
            try{ Thread.sleep( 1000 ); } catch( Exception e ){ }
            Exportando.fechar(); 
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
        }catch( Exception e ){ } } }.start();
        
        }
    }//GEN-LAST:event_lb_ImpressoraMousePressed

    private void lbFiltro2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFiltro2MousePressed
        if ( TB.tbPesquisa.getSelectedRow() != -1){

            int c = TB.tbPesquisa.getSelectedColumn();

            String str = String.valueOf( TB.tbPesquisa.getValueAt(TB.tbPesquisa.getSelectedRow(), TB.tbPesquisa.getSelectedColumn()) );
            filtroReverso( str, c );
        }
        else{

            Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
            JOPM JOptionPaneMod = new JOPM( 1, "FILTRO REVERSO TABELA\n"

                + "\nPara filtrar dados da tabela 1º selecione uma célula\n"
                + "\nOK Para Prosseguir"
                ,"Class: " + this.getClass().getName(),
                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
    }//GEN-LAST:event_lbFiltro2MousePressed

    private void lbFiltro1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFiltro1MousePressed
        if ( TB.tbPesquisa.getSelectedRow() != -1){

            int c = TB.tbPesquisa.getSelectedColumn();

            String str = String.valueOf( TB.tbPesquisa.getValueAt(TB.tbPesquisa.getSelectedRow(), TB.tbPesquisa.getSelectedColumn()) );
            filtro( str, c );
        }
        else{

            Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
            JOPM JOptionPaneMod = new JOPM( 1, "FILTRA TABELA\n"

                + "\nPara filtrar dados da tabela 1º selecione uma célula\n"
                + "\nOK Para Prosseguir"
                ,"Class: " + this.getClass().getName(),
                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
    }//GEN-LAST:event_lbFiltro1MousePressed

    String filtro = "";
    int    coluna = 1;
    public void filtro( String f, int c ) { filtro = f; coluna = c;    
        try { filtro = f.trim(); } catch( Exception e ){}
        
        new Thread() {   @Override public void run() { try { 
            Exportando Exportando = new Exportando("APLICANDO O FILTRO...");
            Exportando.setVisible(true);
            Exportando.pbg.setMinimum(0);
            
            int numFila = TB.tmPesquisa.getRowCount();
            
            Exportando.pbg.setMaximum( numFila );
            
            Thread.sleep( 1 );
                                                
            for (int i = 0; i < numFila; i++) {     Exportando.pbg.setValue( i );
                
                try{
                    
                    String str = String.valueOf( TB.tmPesquisa.getValueAt(i, coluna) ).trim();  
                    
                    if( !str.equals(filtro) ){
                        
                        TB.tmPesquisa.removeRow( i );       
                        i = -1;
                        //System.out.println( str + "  -------------  " + i + "  " + coluna);
                    }
                } catch( Exception e ){ }
                //System.out.println( "++++++++++++++  " + i + "  " + coluna);
            }

            Exportando.fechar();
            
        } catch( Exception e ){ System.out.println("Exportar - "); e.printStackTrace(); } } }.start();  
    }
    
    String filtroReverso = "";
    int    colunaReverso = 1;
    public void filtroReverso( String f, int c ) { colunaReverso = c;    
        try { filtroReverso = f.trim(); } catch( Exception e ){}
        
        new Thread() {   @Override public void run() { try { 
            Exportando Exportando = new Exportando("APLICANDO O FILTRO...");
            Exportando.setVisible(true);
            Exportando.pbg.setMinimum(0);
            
            int numFila = TB.tmPesquisa.getRowCount();
            
            Exportando.pbg.setMaximum( numFila );
            
            Thread.sleep( 1 );
                                                
            for (int i = 0; i < numFila; i++) {     Exportando.pbg.setValue( i );
                
                try{
                    
                    String str = String.valueOf( TB.tmPesquisa.getValueAt(i, colunaReverso) ).trim();  
                    
                    if( str.equals(filtroReverso) ){
                        
                        TB.tmPesquisa.removeRow( i );       
                        i = -1;
                        //System.out.println( str + "  -------------  " + i + "  " + coluna);
                    }
                } catch( Exception e ){ }
                //System.out.println( "++++++++++++++  " + i + "  " + coluna);
            }

            Exportando.fechar();
            
        } catch( InterruptedException e ){ System.out.println("Exportar - "); e.printStackTrace(); } } }.start();  
    }
    
    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );

            if ( TB.tbPesquisa.getSelectedRow() != -1){
                int coluna = TB.tbPesquisa.getSelectedColumn();
                TableColumn TColuna = TB.tbPesquisa.getColumnModel().getColumn( coluna );
                TB.tbPesquisa.removeColumn( TColuna );
            }
            else{
                
                Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                JOPM JOptionPaneMod = new JOPM( 1, "EXCLUIR COLUNA DA TABELA\n"
                        + "\nPARA EXCLUIR UMA COLUNA\n"
                        + "\nPRIMEIRO SELECIONE UMA\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(),
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
        } catch( InterruptedException e ){  } } }.start();
    }//GEN-LAST:event_jLabel6MousePressed

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
    if ( TB.tbPesquisa.getSelectedRow() != -1){
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 
        
            Exportando = new Exportando("Procurando e Excluindo");
            Exportando.setVisible(true);
            Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );                            
            ////////////////////////////////////////////////////////////////
            String contentsX = jTextPane1.getText().trim();
            
        if(!contentsX.equals("")){    
                
            StringTokenizer st1=new StringTokenizer(contentsX,"\n");
 
            int contadorElenco;
            for (int i = 0; st1.hasMoreTokens(); i++) {
                contadorElenco = 0;
                                            
                String rowstring = st1.nextToken();
//              //System.out.println( "rowstring - " + rowstring );
                try{                            
                    String[] dados = rowstring.split("\t");                        
                    contadorElenco++;
                    if(contadorElenco < 2){
                        String material_procurado = dados[0].trim(); 
                        Exportando.pbg.setValue( TB.tmPesquisa.getRowCount() );
///////////////////////////////////////////////////////////////////////////////////////////////////////////
                        for( int L_i=0; L_i < TB.tmPesquisa.getRowCount(); L_i++ ){
                            Exportando.pbg.setValue(L_i);
                            
                            for( int C_i=0; C_i < TB.tmPesquisa.getColumnCount(); C_i++ ){ try{
                                String strn = TB.tmPesquisa.getColumnName(C_i);
                                
                                if( strn.trim().equalsIgnoreCase("ID") ){
                                    String sap = String.valueOf( TB.tmPesquisa.getValueAt(L_i, C_i) ).trim();
                                
                                    if( material_procurado.equals(sap) ){
                                    
                                        TB.tmPesquisa.removeRow( L_i );
                                    }
                                }
                            } catch( Exception e ){  } }    
                        }                     
/////////////////////////////////////////////////////////////////////////////////////////////////////////// 
                    }                                
                } catch( Exception e ){ e.printStackTrace(); }
                                                
            }   
                
            Exportando.fechar();
            Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
            //ImageIcon icon = new ImageIcon( clazzHome.getResource( imgURLIcon ) );
                JOPM JOptionPaneMod = new JOPM( 1, "EXCLUSÃO DE DADOS\n"
                        + "\nSTATUS DA EXCLUSÃO:"
                        + "\nFINALIZADA COM SUCESSO!\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }     
        else{
            
            Exportando.fechar();
            
            Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
            //ImageIcon icon = new ImageIcon( clazzHome.getResource( imgURLIcon ) );
                JOPM JOptionPaneMod = new JOPM( 1, "EXCLUSÃO DE DADOS\n"
                        + "\nSTATUS DA EXCLUSÃO:"
                        + "\nNÃO HÁ DADOS INFORMADO PARA PROCURA!\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
        } catch( InterruptedException e ){ Exportando.fechar(); } } }.start(); 
    }
    else{
                
        Exportando.fechar();
        
        Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
        JOPM JOptionPaneMod = new JOPM( 1, "EXCLUSÃO DE DADOS\n"
                + "\nPARA EXCLUIR DADOS DA TABELA\n"
                + "\nPRIMEIRO INFORME OS NÚMEROS DOS MATERIAIS\n"
                + "\nOK Para Prosseguir"
                ,"Class: " + this.getClass().getName(),
                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
    }//GEN-LAST:event_jLabel9MousePressed

    private void lbLinha_TabelaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbLinha_TabelaMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );

            if ( TB.tbPesquisa.getSelectedRow() != -1){
                TB.tabelaModoDeSelecao( "Column Selection",true, false, false );
            }
            else{
                
                Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                JOPM JOptionPaneMod = new JOPM( 1, "ALTERAR MODO DE SELEÇÃO\n"
                        + "\nPARA ALTERAR O MODO DE SELEÇÃO\n"
                        + "\nPRIMEIRO SELECIONE UMA CÉLULA\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(),
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
        } catch( InterruptedException e ){  } } }.start();           
    }//GEN-LAST:event_lbLinha_TabelaMousePressed

    private void lbColuna_TabelaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbColuna_TabelaMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );

            if ( TB.tbPesquisa.getSelectedRow() != -1){
                TB.tabelaModoDeSelecao( "Row Selection",false, true, false );
            }
            else{
                
                Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                JOPM JOptionPaneMod = new JOPM( 1, "ALTERAR MODO DE SELEÇÃO\n"
                        + "\nPARA ALTERAR O MODO DE SELEÇÃO\n"
                        + "\nPRIMEIRO SELECIONE UMA CÉLULA\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(),
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
        } catch( InterruptedException e ){  } } }.start();   
    }//GEN-LAST:event_lbColuna_TabelaMousePressed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        if( jLabel8.isEnabled() == true ){
            
            copiar_Dados_Da_Memoria_Ou_Do_JTextPane(2);
        }
    }//GEN-LAST:event_jLabel8MousePressed

    private void jLabel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MousePressed

        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );

            if( jLabel7.isEnabled() == true ) { jLabel7.setEnabled(false);

                JFileChooserJM JFileChooserJM = new JFileChooserJM( "  imagens    -   jmarysystems.blogspot.com.br", new String [] { "XLS" , "XLSX" } );
                String strdevolvida = JFileChooserJM.getString( 2 );

                //tfPesquisa.setText( strdevolvida );

                if( !strdevolvida.equals("") ){
                    
                    ImportarExportarExcel ImportarDadosDoExcel = new ImportarExportarExcel();            
                    ImportarDadosDoExcel.Importar( new File( strdevolvida ), TB.tbPesquisa );
                }
                jLabel7.setEnabled(true);
            }
        } catch( Exception e ){ jLabel7.setEnabled(true); } } }.start();
        /*
        Class<imagens_internas.Imagens_Internas> clazzHome = imagens_internas.Imagens_Internas.class;
                    JOPM JOptionPaneMod = new JOPM( 1, "ACESSO NÃO AUTORIZADO\n"
                            + "\nSTATUS DO ACESSO:"
                            + "\nVOCÊ NÃO TEM ACESSO A ESTA SOLICITAÇÃO!\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        */                      
    }//GEN-LAST:event_jLabel7MousePressed
       
    
    private URL       imgURL; 
    private ImageIcon icon;            
    private URL       imgURL2;
    private ImageIcon icon2;
    private boolean cimabaixo = true; 
    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        try{

            if( cimabaixo == false ){
                cimabaixo = true;
                
                jLabel3.setToolTipText( "Ocultar Submenu" );
                jLabel3.setIcon( icon2 );              
                
                jp_Opcoes_Tabela2.setVisible(true);
            } else if( cimabaixo == true ){
                cimabaixo = false;
                                
                jLabel3.setToolTipText( "Mostrar Submenu" );
                jLabel3.setIcon( icon );  
                
                jp_Opcoes_Tabela2.setVisible(false);
            }
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "tabJanelaSubmenu, \n"
                + e.getMessage() + "\n", "Alterar_Seta_Submenu" ); }
    }//GEN-LAST:event_jLabel3MousePressed

    private void jButton6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MousePressed
        try {
                                    
            try {
                
                if( jButton6.isEnabled() == true ){
                    
                    jButton6.setEnabled(false);
                    
                    Verificar_Autorizacao Verificar_Autorizacao = new Verificar_Autorizacao();
                    if( Verificar_Autorizacao.verificar("PRODUTOS", "CONSULTAR") == true ){
                
                        consultar();
                    }
                    else{                        
                        Verificar_Autorizacao.sem_acesso();
                    }
                }
            } catch( Exception e ){}
            
            jButton6.setEnabled(true);
            
        } catch( Exception e ){} 
    }//GEN-LAST:event_jButton6MousePressed

    private void consultar() {                                      
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
        
        String tipoConsulta = "";
        try{ tipoConsulta = jComboBox2.getSelectedItem().toString().trim(); } catch( Exception e ){}
    
        Object[] options = {
            "Confirmar",
            "Cancelar" 
        };
        int n = JOptionPane.showOptionDialog(null,
            "Confirme a Opção de Consulta. \n"+tipoConsulta,
            "Opção de Consulta",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
//////////////////////////////////////////////////////
    if(n==0){    
        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            TB.setar_Cabecalho_da_Tabela(1);
            TB.setar_DefaultTableModel_tbPreferedSize(1);
                
            Exportando = new Exportando("CONSULTANDO");
            Exportando.setVisible(true);
            Exportando.pbg.setMinimum(0); 
            
            /*                
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable contents = clipboard.getContents(null);
            boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
            if ( hasTransferableText ) {
                try { 
                    contentsX = (String)contents.getTransferData(DataFlavor.stringFlavor);
                }catch (Exception e){}
            }    
            */
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
            
        String contentsX = jTextPane1.getText().trim();
        if( contentsX.equals("") ){
            
            if( tipoConsulta.equals("TUDO") ){
                
                List<Produtos> Produtos = null;
                try{ 
                    Query q = JPAUtil.em().createNativeQuery("SELECT * FROM JM.PRODUTOS", Produtos.class );
                    List<Produtos> lista_Banco = q.getResultList();   
                    Produtos = lista_Banco;
                }catch( Exception e ){ }
                
                String rbusca = ""; try{ rbusca = Produtos.get(0).getDescricao(); }catch( Exception e ){}
                if( !rbusca.equals("") ){
                    
                    Exportando.pbg.setMaximum( Produtos.size() );
                    
                    for (int it = 0; it < Produtos.size(); it++){
                        Exportando.pbg.setValue( it );
                        
                        setar_na_tabela( Produtos.get(it) );
                    }
                }
                
                Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
            //ImageIcon icon = new ImageIcon( clazzHome.getResource( imgURLIcon ) );
                    JOPM JOptionPaneMod = new JOPM( 1, "CONSULTA\n"
                            + "\nSTATUS DA CONSULTA:"
                            + "\nCONSULTA REALIZADA COM SUCESSO!\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
            try{ Exportando.fechar(); } catch( Exception e ){}
        }
        else if( !contentsX.equals("") ){           
            
            if( tipoConsulta.equals("SQL") ){
                
                Verificar_Autorizacao Verificar_Autorizacao = new Verificar_Autorizacao();
                if( Verificar_Autorizacao.verificar("PRODUTOS", "CONSULTA_SQL") == true ){
                
                    tabelaResultSet(contentsX);
                }
                else{
                        
                    Verificar_Autorizacao.sem_acesso();
                }
            }            
            else{
                
                try{                                                                            
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                       
                    StringTokenizer stX=new StringTokenizer(contentsX,"\n");                
                    ///////////////////////////////////////////////////////////////
                
                    int countColuna = 0;
                    for( int i=0; stX.hasMoreTokens(); i++ ){               
                                       
                        String rowstring = stX.nextToken();
                        StringTokenizer st2 = new StringTokenizer(rowstring,"\t");
                        for( int j=0; st2.hasMoreTokens(); j++ ){
                        
                            if( i > 0 ){ break; }
                        
                                countColuna += 1;
                                String cellstring = st2.nextToken();
                        }                                                                                                    
                    }
                
                    StringTokenizer stY=new StringTokenizer(contentsX,"\n");
                    ///////////////////////////////////////////////////////////////
                    int countLinha = 0;
                    for( int i=0; stY.hasMoreTokens(); i++ ){               
                                        
                        String rowstring = stY.nextToken();
                        countLinha += 1;                                                                                                
                    }
                    ///////////////////////////////////////////////////////////////////////////////////////////////////////////                
                    Exportando.pbg.setMaximum(countLinha );
                    System.out.println("QTD. Linhas: "+countLinha + " - QTD. Colunas: "+countColuna);
                    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      

                    StringTokenizer st1=new StringTokenizer(contentsX,"\n");
                    ///////////////////////////////////////////////////////////////
                    for (int i = 0; st1.hasMoreTokens(); i++) {
                        Exportando.pbg.setValue( i );
                                            
                        String rowstring = st1.nextToken();
//                      //System.out.println( "rowstring - " + rowstring );
                        try{
                            
                            String[] dados = rowstring.split("\t");                        
                            String material_procurado = dados[0].trim();                            
///////////////////////////////////////////////////////////////////////////////////////////////////////////
                            List<Produtos> Bean_A_Ser_Criado = null;
                                                                
                            if( tipoConsulta.equals("CÓDIGO AUXILIAR") ){
                                    
                                try{ 
                                    
                                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Produtos.class, JPAUtil.em());
                                    Bean_A_Ser_Criado = (List<Produtos>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Produtos.class, 
                                            "SELECT * FROM JM.PRODUTOS WHERE CODIGO_AUXILIAR = ?", material_procurado );
                                }catch( Exception e ){ }
                                    
                                String rbusca = ""; try{ rbusca = Bean_A_Ser_Criado.get(0).getDescricao(); }catch( Exception e ){}
                                if( !rbusca.equals("") ){
                                        
                                    setar_na_tabela( Bean_A_Ser_Criado.get(0) );
                                } 
                                else{
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
                                    setar_linha_em_branco( material_procurado );
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                    
                                }
                            }
                            else if( tipoConsulta.equals("ID") ){
                                    
                                try{ 
                                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Produtos.class, JPAUtil.em());
                                    Bean_A_Ser_Criado = (List<Produtos>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Produtos.class, 
                                            "SELECT * FROM JM.PRODUTOS WHERE ID = ?", material_procurado );
                                }catch( Exception e ){ }
                                    
                                String rbusca = ""; try{ rbusca = Bean_A_Ser_Criado.get(0).getDescricao(); }catch( Exception e ){}
                                if( !rbusca.equals("") ){
                                        
                                    setar_na_tabela( Bean_A_Ser_Criado.get(0) );
                                } 
                                else{
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                    
                                    setar_linha_em_branco( material_procurado );
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                    
                                }                               
                            }
                            else if( tipoConsulta.equals("DESCRIÇÃO") ){
                                    
                                try{ 
                                    DAOGenericoJPA DAOGenericoJPAXX = new DAOGenericoJPA(Produtos.class, JPAUtil.em());
                                    Bean_A_Ser_Criado = (List<Produtos>) DAOGenericoJPAXX.getBeansCom_1_Parametro(Produtos.class, 
                                            "SELECT * FROM JM.PRODUTOS WHERE DESCRICAO LIKE ?", material_procurado.toUpperCase() );
                                }catch( Exception e ){ }
                                    
                                String rbusca = ""; try{ rbusca = Bean_A_Ser_Criado.get(0).getDescricao(); }catch( Exception e ){}
                                if( !rbusca.equals("") ){
                                        
                                    for (int it = 0; it < Bean_A_Ser_Criado.size(); it++){

                                        setar_na_tabela( Bean_A_Ser_Criado.get(it) );
                                    }
                                }
                                else{
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                            
                                    setar_linha_em_branco( material_procurado );  
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                    
                                } 
                            }
                            else if( tipoConsulta.equals("TUDO") ){
                                
                                try{ 
                                    Query q = JPAUtil.em().createNativeQuery("SELECT * FROM JM.PRODUTOS", Produtos.class );
                                    List<Produtos> lista_Banco = q.getResultList();   
                                    Bean_A_Ser_Criado = lista_Banco;
                                }catch( Exception e ){ }
                                
                                String rbusca = ""; try{ rbusca = Bean_A_Ser_Criado.get(0).getDescricao(); }catch( Exception e ){}
                                
                                if( !rbusca.equals("") ){
                                    
                                    Exportando.pbg.setMaximum( Bean_A_Ser_Criado.size() );
                                    
                                    for (int it = 0; it < Bean_A_Ser_Criado.size(); it++){
                                        Exportando.pbg.setValue( it );
                                        setar_na_tabela( Bean_A_Ser_Criado.get(it) );
                                    }
                                }
                            }                            
                        } catch( Exception e ){ e.printStackTrace(); }
                                                
                    }   
                                                
                    Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                    //ImageIcon icon = new ImageIcon( clazzHome.getResource( imgURLIcon ) );
                    JOPM JOptionPaneMod = new JOPM( 1, "CONSULTA\n"
                            + "\nSTATUS DA CONSULTA:"
                            + "\nCONSULTA REALIZADA COM SUCESSO!\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                } catch( Exception e ){ Exportando.fechar(); } 
            }
            
            try{ Exportando.fechar(); } catch( Exception e ){}
        }
        else{
                
            JOPM JOptionPaneMod = new JOPM( 2, "CONSULTAR, "
                    + "\nCampo Vazio"
                    + "\nNenhum dado encontrado no Campo."
                    + "\n", "CONSULTAR" );
        }
    }
    }catch( HeadlessException | InterruptedException e ){
        e.printStackTrace();
        JOPM JOptionPaneMod = new JOPM( 2, "CONEXÃO COM BANCO DE DADOS, "
                + "\nERRO NA CONEXÃO"
                + "\nurlAlone: " + DB_Bean.urlAlone
                + "\nurlNetwork: " + DB_Bean.urlNetwork
                + "\nCONEXÃO NÃO ESTABELECIDA"
                + "\n", "CONEXÃO COM BANCO DE DADOS" );    
    } } }.start();  
    } 
    
    private void setar_na_tabela(Produtos Bean_Recebido){
        try{      
            
            int id = 0;
            String codigoAuxiliar = "";
            String descricao = "";   
            
//////////////////////////////////////////////////////////////////////////////////////////////////////////                                                              
            try{ id                       = Bean_Recebido.getId();                                           }catch( Exception e ){}
            try{ codigoAuxiliar           = Bean_Recebido.getCodigoAuxiliar();                               }catch( Exception e ){}
            try{ descricao                = Bean_Recebido.getDescricao();                                    }catch( Exception e ){}                                            
///////////////////////////////////////////////////////////////////////////////////////////////////////////   

            Object[] rowData = new Object[] { new ImageIcon( Imagens_Menu_Norte.class.getResource("lixo2.png") ),
                id, 
                codigoAuxiliar, 
                descricao
            };            
            TB.tmPesquisa.addRow( rowData ); 
        } catch( Exception e ){}
    }
    
    private void setar_linha_em_branco(String material_procurado){
        try{      
            
            Object[] rowData = new Object[] { new ImageIcon( Imagens_Menu_Norte.class.getResource("lixo2.png") ),
                "", 
                material_procurado, 
                ""
            };            
            TB.tmPesquisa.addRow( rowData ); 
        } catch( Exception e ){}
    }
    
    private void jTextArea5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea5MouseEntered

    }//GEN-LAST:event_jTextArea5MouseEntered

    private void jTextArea5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea5MouseExited

    }//GEN-LAST:event_jTextArea5MouseExited

    private void jTextArea5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea5MousePressed
        copiar_Dados_Da_Memoria_Ou_Do_JTextPane(1);  
    }//GEN-LAST:event_jTextArea5MousePressed

    private void JPOpcao_5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_5MouseEntered

    }//GEN-LAST:event_JPOpcao_5MouseEntered

    private void JPOpcao_5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_5MouseExited

    }//GEN-LAST:event_JPOpcao_5MouseExited

    private void JPOpcao_5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_5MousePressed

    }//GEN-LAST:event_JPOpcao_5MousePressed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void lbEditarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbEditarMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
        
            Exportando = new Exportando("ABRINDO...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
            
            if ( TB.tbPesquisa.getSelectedRow() != -1){
                
                if( lbVisualizar.isEnabled() == true ){
            
                    List<Produtos>  List_UsuarioSistema = null;
                    int id = 0;
                    
                    try{ 
                        int linhaSelecionada = TB.tbPesquisa.getSelectedRow();//pegar a linha selecionada  
                        id = (int) TB.tbPesquisa.getValueAt(linhaSelecionada, 0);//pegar os valores da linha e coluna 
                    }catch(Exception e){}
                    
                    System.out.println("ID DA TABELA SELECIONADA: " + id);
                    
                    List<Produtos> List_2_UsuarioSistema = null;
                    try{
                        Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM JM.USUARIO_SISTEMA WHERE ID = ?", Produtos.class );
                        q2.setParameter( 1, id ); 
                        List_2_UsuarioSistema = q2.getResultList();
                    }catch(Exception e){}
                    
                    String rbusca = ""; try{ rbusca = List_2_UsuarioSistema.get(0).getDescricao(); }catch( Exception e ){}
                    if( !rbusca.equals("") ){
                                            
                        Home.ControleTabs.AddTabComControle(jTabbedPane1, "Alterar Usuário", "livroTp.gif", 
                        new Produtos_02_Cadastrar_Visualizar( Home, jTabbedPane1, "Alterando...", List_2_UsuarioSistema.get(0) ) );
                    }
                    
                    Exportando.fechar();
                }
            }
            else{
                
                Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                JOPM JOptionPaneMod = new JOPM( 1, "ALTERAR USUÁRIO SELECIONADO\n"
                        + "\nPARA ALTERAR USUÁRIO SELECIONADO\n"
                        + "\nPRIMEIRO SELECIONE UMA CÉLULA\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(),
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
        } catch( InterruptedException e ){ Exportando.fechar(); e.printStackTrace(); } } }.start();
    }//GEN-LAST:event_lbEditarMousePressed

    private void lbDesativarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDesativarMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
        
            Exportando = new Exportando("EXCLUINDO...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
            
            int linhaSelecionada = 0;
            
            if ( TB.tbPesquisa.getSelectedRow() != -1){
                
                if( lbDesativar.isEnabled() == true ){
            
                    List<Produtos>  List_UsuarioSistema = null;
                    int id = 0;
                    
                    try{ 
                        linhaSelecionada = TB.tbPesquisa.getSelectedRow();//pegar a linha selecionada  
                        id = (int) TB.tbPesquisa.getValueAt(linhaSelecionada, 0);//pegar os valores da linha e coluna 
                    }catch(Exception e){}
                    
                    System.out.println("ID DA TABELA SELECIONADA: " + id);
                    
                    List<Produtos> List_2_UsuarioSistema = null;
                    try{
                        Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM JM.USUARIO_SISTEMA WHERE ID = ?", Produtos.class );
                        q2.setParameter( 1, id ); 
                        List_2_UsuarioSistema = q2.getResultList();
                    }catch(Exception e){}
                    
                    String rbusca = ""; try{ rbusca = List_2_UsuarioSistema.get(0).getDescricao(); }catch( Exception e ){}
                    if( !rbusca.equals("") ){
                                            
                        //Home.ControleTabs.AddTabComControle(jTabbedPane1, "Alterar Usuário", "livroTp.gif", 
                        //new Produtos_02_Cadastrar_Visualizar( Home, "Alterando...", List_2_UsuarioSistema.get(0) ) );
                        if( rbusca.equalsIgnoreCase("cleilson") ){
                            
                            Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                            JOPM JOptionPaneMod = new JOPM( 1, "EXCLUINDO USUÁRIO SELECIONADO\n"
                                + "\nUSUÁRIO: " + rbusca 
                               + "\nVOCÊ NÃO TEM AUTORIZAÇÃO PARA EXCLUIR O USUÁRIO CLEILSON"
                                + "\nOK Para Prosseguir"
                                ,"Class: " + this.getClass().getName(),
                            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );  
                        }
                        else{
                            
                            excluir_Imagens( List_2_UsuarioSistema.get(0) );
                            
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( List_2_UsuarioSistema.get(0), JPAUtil.em());
                            DAOGenericoJPA2.excluir();
                        
                            TB.tmPesquisa.removeRow( linhaSelecionada );
                        
                            Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                            JOPM JOptionPaneMod = new JOPM( 1, "EXCLUINDO USUÁRIO SELECIONADO\n"
                                + "\nUSUÁRIO: " + rbusca 
                               + "\nEXCLUIDO COM SUCESSO"
                                + "\nOK Para Prosseguir"
                                ,"Class: " + this.getClass().getName(),
                            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );                        
                        }
                    }
                    
                    Exportando.fechar();
                }
            }
            else{
                
                Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                JOPM JOptionPaneMod = new JOPM( 1, "EXCLUINDO USUÁRIO SELECIONADO\n"
                        + "\nPARA EXCLUIR UM USUÁRIO SELECIONADO\n"
                        + "\nPRIMEIRO SELECIONE UMA CÉLULA\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(),
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
        } catch( InterruptedException e ){ Exportando.fechar(); e.printStackTrace(); } } }.start();
    }//GEN-LAST:event_lbDesativarMousePressed

    private void excluir_Imagens(Produtos UsuarioSistema_Excluir_Imagens){ 
    /*new Thread() {   @Override public void run() {*/ try { 
             
        List<UsuarioImagens> lista_Banco = null;
        try{ 
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM JM.USUARIO_IMAGENS WHERE ID_USUARIO_SISTEMA = ?", UsuarioImagens.class );
            q.setParameter( 1, UsuarioSistema_Excluir_Imagens.getId() );
            lista_Banco = q.getResultList();   
        }catch( Exception e ){ }
        
        String rbusca = ""; 
        try{ rbusca = lista_Banco.get(0).getNome(); }catch( Exception e ){}
            
        if( !rbusca.equals("") ){	 
	          
            try{
                
                for (int i=0; i < lista_Banco.size(); i++) {
                    
                    DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( lista_Banco.get(i), JPAUtil.em());
                    DAOGenericoJPA2.excluir();
                }
	    }catch(Exception e){ e.printStackTrace(); }  
        }
                            
    } catch( Exception e ){ } //} }.start();
    }
    
    private void lbVisualizarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbVisualizarMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
        
            Exportando = new Exportando("ABRINDO...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
            
            if ( TB.tbPesquisa.getSelectedRow() != -1){
                
                if( lbVisualizar.isEnabled() == true ){
            
                    List<Produtos>  List_UsuarioSistema = null;
                    int id = 0;
                    
                    try{ 
                        int linhaSelecionada = TB.tbPesquisa.getSelectedRow();//pegar a linha selecionada  
                        id = (int) TB.tbPesquisa.getValueAt(linhaSelecionada, 0);//pegar os valores da linha e coluna 
                    }catch(Exception e){}
                    
                    System.out.println("ID DA TABELA SELECIONADA: " + id);
                    
                    List<Produtos> List_2_UsuarioSistema = null;
                    try{
                        Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM JM.USUARIO_SISTEMA WHERE ID = ?", Produtos.class );
                        q2.setParameter( 1, id ); 
                        List_2_UsuarioSistema = q2.getResultList();
                    }catch(Exception e){}
                    
                    String rbusca = ""; try{ rbusca = List_2_UsuarioSistema.get(0).getDescricao(); }catch( Exception e ){}
                    if( !rbusca.equals("") ){
                                            
                        Home.ControleTabs.AddTabComControle(jTabbedPane1, "Visualizar Usuário", "livroTp.gif", 
                        new Produtos_02_Cadastrar_Visualizar( Home, jTabbedPane1, "Visualizando...", List_2_UsuarioSistema.get(0) ) );
                    }
                    
                    Exportando.fechar();
                }
            }
            else{
                
                Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                JOPM JOptionPaneMod = new JOPM( 1, "VISUALIZAR USUÁRIO SELECIONADO\n"
                        + "\nPARA VISUALIZAR USUÁRIO SELECIONADO\n"
                        + "\nPRIMEIRO SELECIONE UMA CÉLULA\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(),
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
        } catch( InterruptedException e ){ Exportando.fechar(); e.printStackTrace(); } } }.start();        
    }//GEN-LAST:event_lbVisualizarMousePressed

    private void lbSelecionarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSelecionarMousePressed
        
        selecionar_Usuario();
 
    }//GEN-LAST:event_lbSelecionarMousePressed
    
    private void selecionar_Usuario() {                                          
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
                                
            if ( TB.tbPesquisa.getSelectedRow() != -1){
                Exportando = new Exportando("ABRINDO...");
                Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                Exportando.pbg.setMaximum( 100 );
                Exportando.pbg.setValue( 50 );
                
                if( lbVisualizar.isEnabled() == true ){
            
                    List<Produtos>  List_Produtos = null;
                    int id = 0;
                    
                    try{ 
                        int linhaSelecionada = TB.tbPesquisa.getSelectedRow();//pegar a linha selecionada  
                        id = (int) TB.tbPesquisa.getValueAt(linhaSelecionada, 0);//pegar os valores da linha e coluna 
                    }catch(Exception e){}
                    
                    System.out.println("ID DA TABELA SELECIONADA: " + id);
                    
                    List<Produtos> List_2_Produtos = null;
                    try{
                        Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM JM.USUARIO_SISTEMA WHERE ID = ?", Produtos.class );
                        q2.setParameter( 1, id ); 
                        List_2_Produtos = q2.getResultList();
                    }catch(Exception e){}
                    
                    String rbusca = ""; try{ rbusca = List_2_Produtos.get(0).getDescricao(); }catch( Exception e ){}
                    if( !rbusca.equals("") ){
                        
                        //Controle_De_Acesso_02_Cadastrar_Visualizar.tf_USUARIO.setText( List_2_Produtos.get(0).getDescricao() );
                        //Controle_De_Acesso_02_Cadastrar_Visualizar.UsuarioSistemaQueVaiAcessar = List_2_Produtos.get(0);       
                        
                        //Home.ControleTabs.removerTabSemControleSelecionado(jTabselecionar_usuario);
                    }
                    
                    Exportando.fechar();
                }
            }
            else{
                
                Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                JOPM JOptionPaneMod = new JOPM( 1, "SELECIONAR USUÁRIO SELECIONADO\n"
                        + "\nPARA SELECIONAR USUÁRIO\n"
                        + "\nPRIMEIRO SELECIONE UMA CÉLULA\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(),
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
        } catch( InterruptedException e ){ Exportando.fechar(); e.printStackTrace(); } } }.start();        
    }
    
    private void setarUrl_e_ImageIcon_Seta_Inicio(){
        try{                       
            
            imgURL  = Imagens_Menu_Norte.class.getResource("seta_para_baixo.png");
            icon    = new ImageIcon( imgURL );  
            
            imgURL2  = Imagens_Menu_Norte.class.getResource("seta_para_cima.png");
            icon2   = new ImageIcon( imgURL2 ); 
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "setarUrl_e_ImageIcon(), \n"
                + e.getMessage() + "\n", "Alterar_Seta_Submenu" ); }
    }
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPOpcao_5;
    private javax.swing.JPanel Pergunta;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JPanel jp_Opcoes;
    private javax.swing.JPanel jp_Opcoes_Tabela;
    private javax.swing.JPanel jp_Opcoes_Tabela2;
    private javax.swing.JPanel jp_Tabela;
    private javax.swing.JLabel lbColuna_Tabela;
    private javax.swing.JLabel lbDesativar;
    private javax.swing.JLabel lbEditar;
    public javax.swing.JLabel lbFiltro1;
    public javax.swing.JLabel lbFiltro2;
    private javax.swing.JLabel lbLinha_Tabela;
    private javax.swing.JLabel lbSelecionar;
    private javax.swing.JLabel lbVisualizar;
    private javax.swing.JLabel lb_Exportar_Exel;
    private javax.swing.JLabel lb_Impressora;
    // End of variables declaration//GEN-END:variables
 
    private void tabelaResultSet( String query ){ try {   
        while ( TB.tmPesquisa.getRowCount() > 0){ TB.tmPesquisa.removeRow(0); }                   
        DB DB = new DB();
        Connection con = null; try{ con = DB.derby();             }catch(Exception e){}    
        Statement stmt = null; try{ stmt = con.createStatement(); }catch(Exception e){}        
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData rsMetaData = rs.getMetaData(); 

        int columnCount = rsMetaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        
        int contador = 0;
        for (int i = 0; i < columnCount; i++) {
            contador = 1 + i;
            columnNames [i] = rsMetaData.getColumnName(contador);
        }
            
        TB.tmPesquisa = new DefaultTableModel( null, columnNames );
        TB.tbPesquisa.setModel(TB.tmPesquisa);
        
        while (rs.next()) { 
            
            String[] dados = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                contador = 1 + i;
                dados [i] = ( rs.getString(contador) );
            }
            
            TB.tmPesquisa.addRow( dados ); 
        }
        
            Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
            //ImageIcon icon = new ImageIcon( clazzHome.getResource( imgURLIcon ) );
                    JOPM JOptionPaneMod = new JOPM( 1, "CONSULTA\n"
                            + "\nSTATUS DA CONSULTA:"
                            + "\nCONSULTA REALIZADA COM SUCESSO!\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
    } catch( Exception e ){ e.printStackTrace(); } }  
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void consultaSqlPorSap(){
                      
        new Thread() {   @Override public void run() { try {  
            /*
            String tabela = "";
            try{ tabela = jComboBox1.getSelectedItem().toString().trim(); } catch( Exception e ){}
        
            if( !tabela.equals( "" ) ){
                
                String query = "SELECT * FROM JM."+tabela+"";
                                               
                String str[] = jTextPane1.getText().split("\n");
                
                String colunaParaProcurar = jTextField2.getText().trim();
            
                tabelaResultSetComListSap(query, tabela, colunaParaProcurar, str);
            }
           */
        } catch( Exception e ){ e.printStackTrace(); } } }.start();               
    }
    
    public void tabelaResultSetComListSap( String query, String tabela, String colunaParaProcurar, String str[]  ){ try {   
        while ( TB.tmPesquisa.getRowCount() > 0){ TB.tmPesquisa.removeRow(0); }                   
        DB DB = new DB();
        Connection con = null; try{ con = DB.derby();             }catch(Exception e){}    
        Statement stmt = null; try{ stmt = con.createStatement(); }catch(Exception e){}        
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData rsMetaData = rs.getMetaData(); 

        int columnCount = rsMetaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        
        int colunaProcurada = -1;
        
        int contador = 0;
        for (int i = 0; i < columnCount; i++) {
            contador = 1 + i;
            columnNames [i] = rsMetaData.getColumnName(contador);
            
            //////////////////////////
            String sapPROC = ""; try{ sapPROC = rsMetaData.getColumnName(contador); } catch(Exception e){}
            if( sapPROC.equals(colunaParaProcurar) ){
                
                colunaProcurada = i;
            }
        }
            
        TB.tmPesquisa = new DefaultTableModel( null, columnNames );
        TB.tbPesquisa.setModel(TB.tmPesquisa);
        
        if( !str.equals("") ){ for (int xi = 0; xi < str.length; xi++){
            String busca = ""; try{ busca = str[xi].trim(); }catch( Exception e ){ }
            
            boolean novaLinhaBusca = false;
            
            rs = null;
            
            try{
                String queryX = "SELECT * FROM JM."+tabela+" WHERE "+ colunaParaProcurar +" = '"+busca+"'";
                DB = new DB();
                con = null; try{ con = DB.derby();             }catch(Exception e){}    
                stmt = null; try{ stmt = con.createStatement(); }catch(Exception e){}        
                rs = stmt.executeQuery(queryX);
            }catch( Exception e ){ }
                                        
            while (rs.next()) { 
                
                novaLinhaBusca = true;
                        
                boolean buscaEncontrada = false;
                //System.out.println("SAP Procurado: " +busca);
                                    
                String[] dados = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    contador = 1 + i;
                    dados [i] = ( rs.getString(contador) );
                
                    ////////////////////////////////
                    if( i == colunaProcurada ){
                    
                        if( rs.getString(contador).equals(busca) ){
                            
                            buscaEncontrada = true;
                        }
                    }
                }
            
                if( buscaEncontrada == true ){
                    TB.tmPesquisa.addRow( dados ); 
                }
            }  
                
            if( novaLinhaBusca == false ){
               String[] dados = new String[columnCount];
               dados [colunaProcurada] = busca;
               
               TB.tmPesquisa.addRow( dados ); 
               //System.out.println("rs != null: ");
            } 

        }}
        
        TB.setar_DefaultTableModel_tbPreferedSize(1);
        
        Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
        //ImageIcon icon = new ImageIcon( clazzHome.getResource( imgURLIcon ) );
        JOPM JOptionPaneMod = new JOPM( 1, "CONSULTA SQL\n"
                + "\nSTATUS DA CONSULTA:"
                + "\nCONSULTA FINALIZADA COM SUCESSO!\n"
                + "\nOK Para Prosseguir"
                ,"Class: " + this.getClass().getName(), 
                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );

    } catch( SQLException e ){ JOPM JOptionPaneMod = new JOPM( 2, "CONSULTA SQL, \n" + "\n", e.getMessage() ); } }   
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    int opcao_copiar_Dados_Da_Memoria = 0;
    private void copiar_Dados_Da_Memoria_Ou_Do_JTextPane(int opcao_copiar_Dados_Da_Memoria2){  
        
        opcao_copiar_Dados_Da_Memoria = opcao_copiar_Dados_Da_Memoria2;
        
    new Thread() {   @Override public void run() { try {  
        
        int opcao = opcao_copiar_Dados_Da_Memoria;
          
        try {                                                
            int response = JOptionPane.showConfirmDialog(null, ""
                + "**CONFIRMAR LISTAGEM DOS DADOS**"
                + "\n"
                + "E PESQUISAR?");
              
            if( response == JOptionPane.YES_OPTION){
                
                Exportando = new Exportando("Listando Dados");
                Exportando.setVisible(true);
                Exportando.pbg.setMinimum(0);
                Exportando.pbg.setMaximum( 100 );
                Exportando.pbg.setValue( 50 );
                                
                ////////////////////////////////////////////////////////////////
                TB.setar_Cabecalho_da_Tabela(1);
                TB.setar_DefaultTableModel_tbPreferedSize(1);
                
                String contentsX = "";                
                switch(opcao){
                    
                    case 1:
                        contentsX = jTextPane1.getText().trim(); 
                    break;
                    
                    case 2:
                
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        Transferable contents = clipboard.getContents(null);
                        boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
                        if ( hasTransferableText ) {
                            try { 
                                contentsX = (String)contents.getTransferData(DataFlavor.stringFlavor);
                            }catch (Exception e){}
                        }   
                    break;
                }
                
                /////////////////////////////////////////////////////////////// 
                /*
                StringTokenizer stX=new StringTokenizer(contentsX,"\n");                
                
                int countColuna = 0;
                for( int i=0; stX.hasMoreTokens(); i++ ){               
                                        
                    String rowstring = stX.nextToken();
                    StringTokenizer st2 = new StringTokenizer(rowstring,"\t");
                    for( int j=0; st2.hasMoreTokens(); j++ ){
                        
                        if( i > 0 ){ break; }
                        
                        countColuna += 1;
                        String cellstring = st2.nextToken();
                    }                                                                                                    
                }
                */ 
                ////////////////////////////////////////////////////////////////
                
                StringTokenizer st1=new StringTokenizer(contentsX,"\n");
 
                for (int i = 0; st1.hasMoreTokens(); i++) {
                                            
                    String rowstring = ""; try{ rowstring = st1.nextToken(); }catch( Exception e ){} 
//                  //System.out.println( "rowstring - " + rowstring );
                    try{
                            
                        String[] dados = null; try{ dados = rowstring.split("\t"); }catch( Exception e ){}
                            String material_procurado = ""; try{ material_procurado = dados[0].trim(); }catch( Exception e ){}    
                /////////////////////////////////////////////////
                        String id  = "";                         try{ id                          = dados[0].trim(); }catch( Exception e ){} 
                        String codigoaux = "";                   try{ codigoaux                   = dados[1].trim(); }catch( Exception e ){}
                        String nome = "";                        try{ nome                        = dados[2].trim(); }catch( Exception e ){}
                        String senha = "";                       try{ senha                       = dados[3].trim(); }catch( Exception e ){}
                        String email = "";                       try{ email                       = dados[4].trim(); }catch( Exception e ){} 
                        String acesso_ao_sistema = "";           try{ acesso_ao_sistema           = dados[5].trim(); }catch( Exception e ){}                          
                        String data_cadastro = "";               try{ data_cadastro               = dados[6].trim(); }catch( Exception e ){} 
                        String data_ultima_alteracao = "";       try{ data_ultima_alteracao       = dados[7].trim(); }catch( Exception e ){} 
                        String data_ultima_alteracao_senha = ""; try{ data_ultima_alteracao_senha = dados[8].trim(); }catch( Exception e ){}
                        String id_usuario_que_cadastrou = "";    try{ id_usuario_que_cadastrou    = dados[9].trim(); }catch( Exception e ){}
                        String id_usuario_que_alterou = "";      try{ id_usuario_que_alterou      = dados[10].trim(); }catch( Exception e ){}
                /////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////
                            /*
                            int    id   = 0; 
                            String nome = "";
                            String senha = "";                      
                            String email = "";
                            String acesso_ao_sistema = "";                            
                            String data_cadastro = "";
                            String data_ultima_alteracao = "";
                            String data_ultima_alteracao_senha = "";
                            String id_usuario_que_cadastrou = "";    
                            String id_usuario_que_alterou = "";
                            try {
                                List<UsuarioSistema> Produtos = null;
                                try{ 
                                    Query q = JPAUtil.em().createNativeQuery("SELECT * FROM JM.USUARIO_SISTEMA WHERE ID = '"+material_procurado+"'", Produtos.class );
                                    Produtos = q.getResultList();
                                }catch( Exception e ){ }
                                
                                String rbusca = ""; try{ rbusca = String.valueOf( Produtos.get(0).getId() ); }catch( Exception e ){}
                                
                                if( !rbusca.equals("") ){
                                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                    
                                    try{ id                            = Produtos.get(0).getId();                                           }catch( Exception e ){}
                                    try{ nome                          = Produtos.get(0).getNome();                                         }catch( Exception e ){}
                                    try{ senha                         = Produtos.get(0).getSenha();                                        }catch( Exception e ){}
                                    try{ email                         = Produtos.get(0).getEmailRecuperacao();                             }catch( Exception e ){}
                                    try{ acesso_ao_sistema             = Produtos.get(0).getPermitirAcessoAoSistema();                      }catch( Exception e ){}                                    
                                    try{ data_cadastro                 = formatter.format( Produtos.get(0).getDataCadastro() );             }catch( Exception e ){}                                    
                                    try{ data_ultima_alteracao         = formatter.format( Produtos.get(0).getDataUltimaAlteracaoSenha());  }catch( Exception e ){}
                                    try{ data_ultima_alteracao_senha   = formatter.format( Produtos.get(0).getDataUltimaAlteracaoSenha());  }catch( Exception e ){}
                                    try{ id_usuario_que_cadastrou      = Produtos.get(0).getIdUsuarioSistemaCadastro(); }catch( Exception e ){}
                                    try{ id_usuario_que_alterou        = Produtos.get(0).getIdUsuarioSistemaAlteracao(); }catch( Exception e ){}
                                }
                            }catch( Exception e ){}
                            */
/////////////////////////////////////////////////////////////////////////////////////////////////////////// 
                            TB.tmPesquisa.addRow(new Object[]{ id, codigoaux, nome, senha, email, acesso_ao_sistema, data_cadastro, data_ultima_alteracao, data_ultima_alteracao_senha, id_usuario_que_cadastrou, id_usuario_que_alterou });                              
                    } catch( Exception e ){ e.printStackTrace(); }
                                                
                }   
                
                Exportando.fechar();
                Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                    JOPM JOptionPaneMod = new JOPM( 1, "CONSULTA O ELENCO\n"
                            + "\nSTATUS DA CONSULTA:"
                            + "\nCONSULTA FINALIZADA COM SUCESSO!\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
        } catch( Exception e ){ Exportando.fechar(); } //} }.start();        
    } catch( Exception e ){ } } }.start();  
    } 
    
            
}