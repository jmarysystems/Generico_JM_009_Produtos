
package produtos;

import br.com.jmary.home.Home;
import br.com.jmary.home.imagens.Imagens_Internas;
import br.com.jmary.home.jpa.DAOGenericoJPA;
import br.com.jmary.home.jpa.JPAUtil;
import br.com.jmary.utilidades.Exportando;
import javax.swing.ImageIcon;
import br.com.jmary.utilidades.JOPM;
import br.com.jmary.utilidades.PopupMenu_Colar;
import controle_de_acesso.Verificar_Autorizacao;
import home_controle_menus_norte.imagens.Imagens_Menu_Norte;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import login_do_sistema.Login;
import produtos_beans.Produtos;
import produtos_beans.ProdutosImagens;
import produtos_imagens.Visualizador_Imagens_Produtos_Banco_de_Dados;

/**
 *
 * @author AnaMariana
 */
public class Produtos_02_Cadastrar_Visualizar extends javax.swing.JPanel {
    
    Home Home;
    String status_cadastro;
    Produtos Classe_Bean_Recebida;
    JTabbedPane JTabbedPane_Recebido;
    PopupMenu_Colar PopupMenu_Colar;
    
    public List<String> lista_de_Endereco_imagemExterna_ = new ArrayList<>();
    
    /** Creates new form SombraVendas
     * @param Home2
     * @param JTabbedPane_Recebido2
     * @param status_cadastro2 */
    public Produtos_02_Cadastrar_Visualizar( Home Home2, JTabbedPane JTabbedPane_Recebido2, String status_cadastro2 ) {
        initComponents();
        
        Home = Home2;  
        status_cadastro = status_cadastro2;
        JTabbedPane_Recebido = JTabbedPane_Recebido2;
        setar_Dados_Iniciais();
        
    }
    
    public Produtos_02_Cadastrar_Visualizar( Home Home2, JTabbedPane JTabbedPane_Recebido2, String status_cadastro2, Produtos UsuarioSistema_Recebido2 ) {
        initComponents();
        
        Home = Home2;  
        status_cadastro = status_cadastro2;
        Classe_Bean_Recebida = UsuarioSistema_Recebido2;
        JTabbedPane_Recebido = JTabbedPane_Recebido2;
        
        setar_Dados_Iniciais();
    }
    
    private void setar_Dados_Iniciais(){
        /*new Thread() {   @Override public void run() {*/ try {  
            
//////////////////////////////////////////////////////////////////////////////////////////////            
            imgURL  = Imagens_Menu_Norte.class.getResource("seta_para_baixo.png");
            icon    = new ImageIcon( imgURL );  
            
            imgURL2  = Imagens_Menu_Norte.class.getResource("seta_para_cima.png");
            icon2   = new ImageIcon( imgURL2 ); 
//////////////////////////////////////////////////////////////////////////////////////////////

            if(status_cadastro.equalsIgnoreCase("Cadastrando...")){
                
                btExcluir.setVisible(false);
                
                mostrar_Ocultar_Jp_Padrao_Tabela();
            }
            else if(status_cadastro.equalsIgnoreCase("Visualizando...")){
                
                btExcluir.setVisible(false);
                btSalvar.setVisible(false);
                
                setar_Visualizacao_Recebida();
                setarImagem_Principal();
                
                desabilitar_componentes();
            }
            else if(status_cadastro.equalsIgnoreCase("Alterando...")){
                
                setar_Visualizacao_Recebida();                
                setarImagem_Principal();
            }
        } catch( Exception e ){  } //} }.start();
    }
    
    public void setarImagem_Principal(){ 
    new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
        
        List<ProdutosImagens> ProdutosImagens = null;
        try{ 
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM JM.PRODUTOS WHERE ID_PRODUTOS = ?", ProdutosImagens.class );
            q.setParameter(1, Classe_Bean_Recebida.getId() );
            List<ProdutosImagens> lista_Banco = q.getResultList();   
            ProdutosImagens = lista_Banco;
            
            System.out.println("setarImagem_Principal() - " + Classe_Bean_Recebida.getId() + " - " + Classe_Bean_Recebida.getDescricao());
        }catch( Exception e ){
            
            System.out.println("List<ProdutosImagens> ProdutosImagens - Erro - " + Classe_Bean_Recebida.getId() +" - "+Classe_Bean_Recebida.getDescricao());
            e.printStackTrace();
        }
        
        String rbusca = ""; 
        try{ rbusca = ProdutosImagens.get(0).getNome(); }catch( Exception e ){}
            
        if( !rbusca.equals("") ){	
            
	    BufferedImage bufImg = null;
            ImageIcon     icon   = null;
            Image         image  = null;
            int widith = 0;
            int height = 0;
            try{
                
                byte[] byte_list = (byte[]) ProdutosImagens.get(0).getImagem();
                //ImageIO.read( img );
                bufImg = ImageIO.read(new ByteArrayInputStream( byte_list )); 
                icon   = new ImageIcon(bufImg);
                image  = icon.getImage();
                //image = icon.getImage();//ImageIO.read(f);  
                widith = lbImagemPrincipal.getPreferredSize().width;
                height = lbImagemPrincipal.getPreferredSize().height;
                lbImagemPrincipal.setIcon(new ImageIcon(image.getScaledInstance(widith, height, Image.SCALE_DEFAULT)));
                //ImageIO.write(img, "PNG", new File("C:/Downloads/h.png"));
                
                System.out.println("setarImagem_Principal() - bufImg = ImageIO.read" );
                System.out.println("setarImagem_Principal() - widith - " + widith);
                System.out.println("setarImagem_Principal() - height - " + height);
            } catch (Exception e) {
                
                System.out.println("setarImagem_Principal() - Erro - bufImg = ImageIO.read" );
                System.out.println("setarImagem_Principal() - Erro - widith - " + widith);
                System.out.println("setarImagem_Principal() - Erro - height - " + height);
                e.printStackTrace();
            }          
        }
        else{
            
            lbImagemPrincipal.setIcon(null);
        }
                            
    } catch( Exception e ){ } } }.start();
    }
    
    public void setarImagemExterna_Endereco_for(File img){ 

        /*new Thread() {   @Override public void run() {*/ try { 
            
            BufferedImage bufImg = null;
            ImageIcon     icon   = null;
            Image         image  = null;
            try{
                        bufImg = ImageIO.read( img );
                        icon   = new ImageIcon(bufImg);
                        image  = icon.getImage();//ImageIO.read(f);  
                    } catch (IOException ex) {}  

                    try{
                
                //image = icon.getImage();//ImageIO.read(f);  
                int widith = lbImagemPrincipal.getWidth();
                int height = lbImagemPrincipal.getHeight();
            
                lbImagemPrincipal.setIcon(new ImageIcon(image.getScaledInstance(
                    widith, height, Image.SCALE_DEFAULT)));
                
		//ImageIO.write(img, "PNG", new File("C:/Downloads/h.png"));
	    }catch(Exception e){ e.printStackTrace(); }  
                
                String nome = img.getName();
                String nomeList[] = nome.split(Pattern.quote("."));             
        } catch( Exception e ){ } 
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
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jp_Padrao_Tabela = new javax.swing.JPanel();
        jpO = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        JPOpcao_14 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        JPOpcao_15 = new javax.swing.JPanel();
        lb_Cadastrado_Por = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        JPOpcao_13 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        JPOpcao_12 = new javax.swing.JPanel();
        lb_Alterado_Por = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        JPOpcao_18 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        JPOpcao_19 = new javax.swing.JPanel();
        lb_Data_Cadastro = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        JPOpcao_16 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        JPOpcao_17 = new javax.swing.JPanel();
        lb_Data_Ultima_Alteracao = new javax.swing.JLabel();
        jpIcon1 = new javax.swing.JPanel();
        lbImagemPrincipal = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        JPOpcao_24 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        JPOpcao_25 = new javax.swing.JPanel();
        tfCodigoAuxiliar = new javax.swing.JTextField();
        jPanel28 = new javax.swing.JPanel();
        JPOpcao_26 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        JPOpcao_27 = new javax.swing.JPanel();
        tfDescricao = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(948, 487));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1170, 1202));

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Produtos - Cadastrar/Visualizar");

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
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jpOLayout = new javax.swing.GroupLayout(jpO);
        jpO.setLayout(jpOLayout);
        jpOLayout.setHorizontalGroup(
            jpOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 913, Short.MAX_VALUE)
        );
        jpOLayout.setVerticalGroup(
            jpOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        JPOpcao_14.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_14.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_14MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_14MousePressed(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel68.setText("Cadastrado Por.: ");

        javax.swing.GroupLayout JPOpcao_14Layout = new javax.swing.GroupLayout(JPOpcao_14);
        JPOpcao_14.setLayout(JPOpcao_14Layout);
        JPOpcao_14Layout.setHorizontalGroup(
            JPOpcao_14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
        );
        JPOpcao_14Layout.setVerticalGroup(
            JPOpcao_14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        JPOpcao_15.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_15.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_15.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_15MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_15MousePressed(evt);
            }
        });

        lb_Cadastrado_Por.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lb_Cadastrado_Por.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout JPOpcao_15Layout = new javax.swing.GroupLayout(JPOpcao_15);
        JPOpcao_15.setLayout(JPOpcao_15Layout);
        JPOpcao_15Layout.setHorizontalGroup(
            JPOpcao_15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_Cadastrado_Por, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_15Layout.setVerticalGroup(
            JPOpcao_15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_Cadastrado_Por, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPOpcao_14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPOpcao_15, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPOpcao_15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JPOpcao_14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JPOpcao_13.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_13.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_13MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_13MousePressed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel60.setText("Alterado Por (Última Alteração).: ");

        javax.swing.GroupLayout JPOpcao_13Layout = new javax.swing.GroupLayout(JPOpcao_13);
        JPOpcao_13.setLayout(JPOpcao_13Layout);
        JPOpcao_13Layout.setHorizontalGroup(
            JPOpcao_13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
        );
        JPOpcao_13Layout.setVerticalGroup(
            JPOpcao_13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        JPOpcao_12.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_12.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_12.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_12MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_12MousePressed(evt);
            }
        });

        lb_Alterado_Por.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lb_Alterado_Por.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout JPOpcao_12Layout = new javax.swing.GroupLayout(JPOpcao_12);
        JPOpcao_12.setLayout(JPOpcao_12Layout);
        JPOpcao_12Layout.setHorizontalGroup(
            JPOpcao_12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_Alterado_Por, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_12Layout.setVerticalGroup(
            JPOpcao_12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_Alterado_Por, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPOpcao_13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPOpcao_12, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPOpcao_12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JPOpcao_13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JPOpcao_18.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_18.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_18MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_18MousePressed(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel72.setText("Data Cadastro.: ");

        javax.swing.GroupLayout JPOpcao_18Layout = new javax.swing.GroupLayout(JPOpcao_18);
        JPOpcao_18.setLayout(JPOpcao_18Layout);
        JPOpcao_18Layout.setHorizontalGroup(
            JPOpcao_18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
        );
        JPOpcao_18Layout.setVerticalGroup(
            JPOpcao_18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        JPOpcao_19.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_19.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_19.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_19MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_19MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_19MousePressed(evt);
            }
        });

        lb_Data_Cadastro.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lb_Data_Cadastro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout JPOpcao_19Layout = new javax.swing.GroupLayout(JPOpcao_19);
        JPOpcao_19.setLayout(JPOpcao_19Layout);
        JPOpcao_19Layout.setHorizontalGroup(
            JPOpcao_19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_Data_Cadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_19Layout.setVerticalGroup(
            JPOpcao_19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_Data_Cadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPOpcao_18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPOpcao_19, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPOpcao_19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JPOpcao_18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JPOpcao_16.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_16.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_16MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_16MousePressed(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel70.setText("Data última Alteração.: ");

        javax.swing.GroupLayout JPOpcao_16Layout = new javax.swing.GroupLayout(JPOpcao_16);
        JPOpcao_16.setLayout(JPOpcao_16Layout);
        JPOpcao_16Layout.setHorizontalGroup(
            JPOpcao_16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
        );
        JPOpcao_16Layout.setVerticalGroup(
            JPOpcao_16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        JPOpcao_17.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_17.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_17.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_17MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_17MousePressed(evt);
            }
        });

        lb_Data_Ultima_Alteracao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lb_Data_Ultima_Alteracao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout JPOpcao_17Layout = new javax.swing.GroupLayout(JPOpcao_17);
        JPOpcao_17.setLayout(JPOpcao_17Layout);
        JPOpcao_17Layout.setHorizontalGroup(
            JPOpcao_17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_Data_Ultima_Alteracao, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_17Layout.setVerticalGroup(
            JPOpcao_17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_Data_Ultima_Alteracao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPOpcao_16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPOpcao_17, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPOpcao_17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JPOpcao_16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jpIcon1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpIcon1.setPreferredSize(new java.awt.Dimension(185, 200));
        jpIcon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpIcon1MousePressed(evt);
            }
        });

        lbImagemPrincipal.setBorder(new javax.swing.border.MatteBorder(null));
        lbImagemPrincipal.setPreferredSize(new java.awt.Dimension(183, 186));

        javax.swing.GroupLayout jpIcon1Layout = new javax.swing.GroupLayout(jpIcon1);
        jpIcon1.setLayout(jpIcon1Layout);
        jpIcon1Layout.setHorizontalGroup(
            jpIcon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbImagemPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpIcon1Layout.setVerticalGroup(
            jpIcon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbImagemPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jp_Padrao_TabelaLayout = new javax.swing.GroupLayout(jp_Padrao_Tabela);
        jp_Padrao_Tabela.setLayout(jp_Padrao_TabelaLayout);
        jp_Padrao_TabelaLayout.setHorizontalGroup(
            jp_Padrao_TabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jp_Padrao_TabelaLayout.createSequentialGroup()
                .addComponent(jpIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jp_Padrao_TabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jp_Padrao_TabelaLayout.setVerticalGroup(
            jp_Padrao_TabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_Padrao_TabelaLayout.createSequentialGroup()
                .addGroup(jp_Padrao_TabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp_Padrao_TabelaLayout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jpO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPOpcao_24.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_24.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_24MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_24MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_24MousePressed(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel78.setText("Código Auxiliar.: ");

        javax.swing.GroupLayout JPOpcao_24Layout = new javax.swing.GroupLayout(JPOpcao_24);
        JPOpcao_24.setLayout(JPOpcao_24Layout);
        JPOpcao_24Layout.setHorizontalGroup(
            JPOpcao_24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );
        JPOpcao_24Layout.setVerticalGroup(
            JPOpcao_24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        JPOpcao_25.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_25.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_25.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_25MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_25MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_25MousePressed(evt);
            }
        });

        tfCodigoAuxiliar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tfCodigoAuxiliar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfCodigoAuxiliar.setPreferredSize(new java.awt.Dimension(209, 25));
        tfCodigoAuxiliar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfCodigoAuxiliarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfCodigoAuxiliarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfCodigoAuxiliarMouseExited(evt);
            }
        });
        tfCodigoAuxiliar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCodigoAuxiliarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout JPOpcao_25Layout = new javax.swing.GroupLayout(JPOpcao_25);
        JPOpcao_25.setLayout(JPOpcao_25Layout);
        JPOpcao_25Layout.setHorizontalGroup(
            JPOpcao_25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpcao_25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfCodigoAuxiliar, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_25Layout.setVerticalGroup(
            JPOpcao_25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_25Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tfCodigoAuxiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPOpcao_24, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPOpcao_25, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPOpcao_25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JPOpcao_24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JPOpcao_26.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_26.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_26MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_26MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_26MousePressed(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel80.setText("Descrição.: ");

        javax.swing.GroupLayout JPOpcao_26Layout = new javax.swing.GroupLayout(JPOpcao_26);
        JPOpcao_26.setLayout(JPOpcao_26Layout);
        JPOpcao_26Layout.setHorizontalGroup(
            JPOpcao_26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );
        JPOpcao_26Layout.setVerticalGroup(
            JPOpcao_26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        JPOpcao_27.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_27.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_27.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_27MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_27MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_27MousePressed(evt);
            }
        });

        tfDescricao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tfDescricao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tfDescricao.setPreferredSize(new java.awt.Dimension(209, 25));
        tfDescricao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfDescricaoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tfDescricaoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tfDescricaoMouseExited(evt);
            }
        });
        tfDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfDescricaoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout JPOpcao_27Layout = new javax.swing.GroupLayout(JPOpcao_27);
        JPOpcao_27.setLayout(JPOpcao_27Layout);
        JPOpcao_27Layout.setHorizontalGroup(
            JPOpcao_27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpcao_27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_27Layout.setVerticalGroup(
            JPOpcao_27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_27Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPOpcao_26, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPOpcao_27, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPOpcao_27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JPOpcao_26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(312, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/jmary/home/imagens/ajuda.gif"))); // NOI18N
        jButton7.setText("Opções/Imagens");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton7MousePressed(evt);
            }
        });

        btSalvar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/Save.png"))); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.setPreferredSize(new java.awt.Dimension(185, 31));
        btSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btSalvarMousePressed(evt);
            }
        });

        btExcluir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/cancel.png"))); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.setPreferredSize(new java.awt.Dimension(185, 31));
        btExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btExcluirMousePressed(evt);
            }
        });

        javax.swing.GroupLayout PerguntaLayout = new javax.swing.GroupLayout(Pergunta);
        Pergunta.setLayout(PerguntaLayout);
        PerguntaLayout.setHorizontalGroup(
            PerguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PerguntaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PerguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jp_Padrao_Tabela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PerguntaLayout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)))
                .addContainerGap())
        );
        PerguntaLayout.setVerticalGroup(
            PerguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PerguntaLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(PerguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jp_Padrao_Tabela, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Cadastrar/Visualizar", Pergunta);

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
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

   
    private URL       imgURL; 
    private ImageIcon icon;            
    private URL       imgURL2;
    private ImageIcon icon2;
    private boolean cimabaixo = true; 
    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        
        mostrar_Ocultar_Jp_Padrao_Tabela();
    }//GEN-LAST:event_jLabel3MousePressed

    private void mostrar_Ocultar_Jp_Padrao_Tabela() {                                     
        try{

            if( cimabaixo == false ){
                cimabaixo = true;
                
                jLabel3.setToolTipText( "Ocultar Submenu" );
                jLabel3.setIcon( icon2 );              
                
                jp_Padrao_Tabela.setVisible(true);
            } else if( cimabaixo == true ){
                cimabaixo = false;
                                
                jLabel3.setToolTipText( "Mostrar Submenu" );
                jLabel3.setIcon( icon );  
                
                jp_Padrao_Tabela.setVisible(false);
            }
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "tabJanelaSubmenu, \n"
                + e.getMessage() + "\n", "Alterar_Seta_Submenu" ); }       
    } 
    
    private void JPOpcao_13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_13MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_13MouseEntered

    private void JPOpcao_13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_13MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_13MouseExited

    private void JPOpcao_13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_13MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_13MousePressed

    private void JPOpcao_12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_12MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_12MouseEntered

    private void JPOpcao_12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_12MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_12MouseExited

    private void JPOpcao_12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_12MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_12MousePressed

    private void JPOpcao_14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_14MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_14MouseEntered

    private void JPOpcao_14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_14MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_14MouseExited

    private void JPOpcao_14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_14MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_14MousePressed

    private void JPOpcao_15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_15MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_15MouseEntered

    private void JPOpcao_15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_15MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_15MouseExited

    private void JPOpcao_15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_15MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_15MousePressed

    private void JPOpcao_16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_16MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_16MouseEntered

    private void JPOpcao_16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_16MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_16MouseExited

    private void JPOpcao_16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_16MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_16MousePressed

    private void JPOpcao_17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_17MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_17MouseEntered

    private void JPOpcao_17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_17MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_17MouseExited

    private void JPOpcao_17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_17MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_17MousePressed

    private void JPOpcao_18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_18MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_18MouseEntered

    private void JPOpcao_18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_18MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_18MouseExited

    private void JPOpcao_18MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_18MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_18MousePressed

    private void JPOpcao_19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_19MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_19MouseEntered

    private void JPOpcao_19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_19MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_19MouseExited

    private void JPOpcao_19MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_19MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_19MousePressed

    private void jButton7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MousePressed

        /*new Thread() {   @Override public void run() {*/ try { 
        
        if(status_cadastro.equalsIgnoreCase("Cadastrando...")){
                  
            Exportando = new Exportando("ABRINDO...");
            Exportando.setVisible(true);
            Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            
            Exportando.pbg.setValue( 50 ); 
            
            Home.ControleTabs.AddTabComControle(jTabbedPane1, "Visualizador Imagens Banco de Dados", "livroTp.gif", 
                        new Visualizador_Imagens_Produtos_Banco_de_Dados(Home, 0, status_cadastro, this ) ); 
            
            Exportando.fechar();             
        }
        else{
      
            Exportando = new Exportando("ABRINDO...");
            Exportando.setVisible(true);
            Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            
            Exportando.pbg.setValue( 50 ); 
            
            Home.ControleTabs.AddTabComControle(jTabbedPane1, "Visualizador Imagens Banco de Dados", "livroTp.gif", 
                        new Visualizador_Imagens_Produtos_Banco_de_Dados(Home, Classe_Bean_Recebida.getId(), status_cadastro, this) );   
            
            Exportando.fechar();  
        }      
            
    }catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); }  //} }.start(); 
    }//GEN-LAST:event_jButton7MousePressed

    private void JPOpcao_24MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_24MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_24MouseEntered

    private void JPOpcao_24MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_24MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_24MouseExited

    private void JPOpcao_24MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_24MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_24MousePressed

    private void tfCodigoAuxiliarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfCodigoAuxiliarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCodigoAuxiliarMouseEntered

    private void tfCodigoAuxiliarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfCodigoAuxiliarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCodigoAuxiliarMouseExited

    private void tfCodigoAuxiliarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCodigoAuxiliarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCodigoAuxiliarKeyReleased

    private void JPOpcao_25MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_25MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_25MouseEntered

    private void JPOpcao_25MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_25MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_25MouseExited

    private void JPOpcao_25MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_25MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_25MousePressed

    private void JPOpcao_26MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_26MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_26MouseEntered

    private void JPOpcao_26MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_26MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_26MouseExited

    private void JPOpcao_26MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_26MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_26MousePressed

    private void tfDescricaoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfDescricaoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDescricaoMouseEntered

    private void tfDescricaoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfDescricaoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDescricaoMouseExited

    private void tfDescricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDescricaoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDescricaoKeyReleased

    private void JPOpcao_27MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_27MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_27MouseEntered

    private void JPOpcao_27MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_27MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_27MouseExited

    private void JPOpcao_27MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_27MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_27MousePressed

    private void jpIcon1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpIcon1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jpIcon1MousePressed

    Exportando Exportando;
    private void btSalvarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSalvarMousePressed
    new Thread() {   @Override public void run() { try { 
        
        if(status_cadastro.equalsIgnoreCase("Cadastrando...")){
            
/* ////// */Classe_Bean_Recebida = new Produtos();
      
            Exportando = new Exportando("CADASTRANDO");
            Exportando.setVisible(true);
            Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            
            Exportando.pbg.setValue( 50 ); 

            setando_os_que_nao_precisam_de_validacao();
            
            Exportando.fechar();             
        }
        else{
      
            Exportando = new Exportando("ALTERANDO");
            Exportando.setVisible(true);
            Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            
            Exportando.pbg.setValue( 50 ); 
 
            setando_os_que_nao_precisam_de_validacao();
            
            Exportando.fechar();  
        }   
    }catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); }  } }.start(); 
    }//GEN-LAST:event_btSalvarMousePressed

    private void btExcluirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExcluirMousePressed
        try { 
    
            Object[] options = {
                "Confirmar",
                "Cancelar" 
            };
            int n = JOptionPane.showOptionDialog(null,
                    "Confirme a Opção de Excluir Este Produto\n"
                    + "Listado Abaixo.",
                    "Opção de Consulta",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
//////////////////////////////////////////////////////
            if(n==0){
        
                excluir_Produto_Atual();
            }            
        } catch( Exception e ){} 
    }//GEN-LAST:event_btExcluirMousePressed

    private void tfDescricaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfDescricaoMouseClicked
        try{
            PopupMenu_Colar = new PopupMenu_Colar(tfDescricao);
        } catch( Exception e ){} 
    }//GEN-LAST:event_tfDescricaoMouseClicked

    private void tfCodigoAuxiliarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfCodigoAuxiliarMouseClicked
        try{
            PopupMenu_Colar = new PopupMenu_Colar(tfCodigoAuxiliar);
        } catch( Exception e ){} 
    }//GEN-LAST:event_tfCodigoAuxiliarMouseClicked
    
    private void excluir_Produto_Atual() {                                       
         new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
        
            Exportando = new Exportando("EXCLUINDO...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
            
                if( btExcluir.isEnabled() == true ){
                                                  
                    List<Produtos> List_2_Produtos = null;
                    try{
                        Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM JM.PRODUTOS WHERE ID = ?", Produtos.class );
                        q2.setParameter(1, Classe_Bean_Recebida.getId() ); 
                        List_2_Produtos = q2.getResultList();
                    }catch(Exception e){}
                    
                    String rbusca = ""; try{ rbusca = List_2_Produtos.get(0).getDescricao(); }catch( Exception e ){}
                    if( !rbusca.equals("") ){
                                            
                        //Home.ControleTabs.AddTabComControle(jTabbedPane1, "Alterar Usuário", "livroTp.gif", 
                        //new Produtos_02_Cadastrar_Visualizar( Home, "Alterando...", List_2_UsuarioSistema.get(0) ) );
                        excluir_Imagens( List_2_Produtos.get(0) );
                            
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( List_2_Produtos.get(0), JPAUtil.em());
                        DAOGenericoJPA2.excluir();

                        Home.ControleTabs.removerTabSemControleSelecionado(JTabbedPane_Recebido);
                            
                        Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "EXCLUINDO PRODUTO SELECIONADO\n"
                            + "\nPRODUTO: " + rbusca 
                            + "\nEXCLUIDO COM SUCESSO"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(),
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );                        
                    }
                    
                    Exportando.fechar();
                }
            
        } catch( InterruptedException e ){ Exportando.fechar(); e.printStackTrace(); } } }.start();
    }
    
    private void excluir_Imagens(Produtos Produtos_Excluir_Imagens){ 
    /*new Thread() {   @Override public void run() {*/ try { 
             
        List<ProdutosImagens> lista_Banco = null;
        try{ 
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM JM.PRODUTOS_IMAGENS WHERE ID_PRODUTOS = ?", ProdutosImagens.class );
            q.setParameter( 1, Produtos_Excluir_Imagens.getId() );
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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPOpcao_12;
    private javax.swing.JPanel JPOpcao_13;
    private javax.swing.JPanel JPOpcao_14;
    private javax.swing.JPanel JPOpcao_15;
    private javax.swing.JPanel JPOpcao_16;
    private javax.swing.JPanel JPOpcao_17;
    private javax.swing.JPanel JPOpcao_18;
    private javax.swing.JPanel JPOpcao_19;
    private javax.swing.JPanel JPOpcao_24;
    private javax.swing.JPanel JPOpcao_25;
    private javax.swing.JPanel JPOpcao_26;
    private javax.swing.JPanel JPOpcao_27;
    private javax.swing.JPanel Pergunta;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpIcon1;
    private javax.swing.JPanel jpO;
    private javax.swing.JPanel jp_Padrao_Tabela;
    public javax.swing.JLabel lbImagemPrincipal;
    private javax.swing.JLabel lb_Alterado_Por;
    private javax.swing.JLabel lb_Cadastrado_Por;
    private javax.swing.JLabel lb_Data_Cadastro;
    private javax.swing.JLabel lb_Data_Ultima_Alteracao;
    public javax.swing.JTextField tfCodigoAuxiliar;
    public javax.swing.JTextField tfDescricao;
    // End of variables declaration//GEN-END:variables
    
    private void setar_Visualizacao_Recebida(){                                          
        new Thread(){   @Override public void run() { try{ 
            
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
            Verificar_Autorizacao Verificar_Autorizacao = new Verificar_Autorizacao();
            try{ lb_Cadastrado_Por.setText( Verificar_Autorizacao.get_UsuarioSistema( Classe_Bean_Recebida.getIdUsuarioSistemaCadastro() ).getLogin() ); }catch(Exception e){} 
            try{ lb_Alterado_Por.setText( Verificar_Autorizacao.get_UsuarioSistema( Classe_Bean_Recebida.getIdUsuarioSistemaAlteracao() ).getLogin() ); }catch(Exception e){}
            
            try{ 
                
                ////////dd.MM.yyyy HH:mm:ss/////////////EEEE, d MMMM yyyy HH:mm:ss
                DateFormat dfmt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                String dataCadastro = ""; 
                try{ dataCadastro = dfmt.format(Classe_Bean_Recebida.getDataCadastro() ); }catch(Exception e){}
                try{ System.out.println("getDataCadastro - " + Classe_Bean_Recebida.getDataCadastro() ); }catch(Exception e){}
                lb_Data_Cadastro.setText( dataCadastro ); 
            }catch(Exception e){} 
            
            try{ 
                
                ////////dd.MM.yyyy HH:mm:ss/////////////EEEE, d MMMM yyyy HH:mm:ss
                DateFormat dfmt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                String data_Ultima_Alteracao = ""; 
                try{ data_Ultima_Alteracao = dfmt.format(Classe_Bean_Recebida.getDataAlteracao()); }catch(Exception e){}
                try{ System.out.println("getDataAlteracao - " + Classe_Bean_Recebida.getDataAlteracao() ); }catch(Exception e){}
                lb_Data_Ultima_Alteracao.setText( data_Ultima_Alteracao ); 
            }catch(Exception e){} 
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            

            try{ tfDescricao.setText(Classe_Bean_Recebida.getDescricao()); }catch(Exception e){}
            try{ tfCodigoAuxiliar.setText(Classe_Bean_Recebida.getCodigoAuxiliar()); }catch(Exception e){}
          
        } catch( Exception e ){  } } }.start();        
    }
    
    private void desabilitar_componentes() {                                          
        new Thread() {   @Override public void run() { try {

            try{ 
                
                tfDescricao.setEditable(false);
                tfCodigoAuxiliar.setEditable(false);
            }catch(Exception e){}
          
        } catch( Exception e ){  } } }.start();        
    }
    
/// CADASTRANDO ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
    private void setando_os_que_nao_precisam_de_validacao(){ 
        
        try{
            
            try{ Classe_Bean_Recebida.setCodigoAuxiliar( tfCodigoAuxiliar.getText().trim().toUpperCase() ); }catch( Exception e ){}
            
            verificar_Usuario_Logado();
            
        }catch( Exception e ){}
    }
    
    private void verificar_Usuario_Logado(){   
        
        String rbusca = ""; 
        try{ rbusca = Login.Usuario_Logado.getLogin(); }catch( Exception e ){}
        
        try{
            
            if( !rbusca.equals("") ){
                
                System.out.println( Login.Usuario_Logado.getLogin() );
                
                verificar_Usuario_Do_Sistema_Que_Cadastrou_E_Alterou();
            }
            else{

                Class<br.com.jmary.home.imagens.Imagens_Internas> clazzHome = br.com.jmary.home.imagens.Imagens_Internas.class;
                JOPM JOptionPaneMod = new JOPM( 1, "STATUS DO CADASTRO\n"
                        + "\n"
                        + "\nNÃO HÁ USUÁRIO LOGADO!\n"
                        + "\n"
                        + "\nPARA CADASTRAR É NECESSÁRIO LOGAR!\n"
                        + "\n"
                        + "\nOK PARA PROSSEGUIR"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
        }catch( Exception e ){ 
            
                e.printStackTrace();
                            
                Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                JOPM JOptionPaneMod = new JOPM( 1, "USUÁRIO LOGADO\n"
                        + "\n"
                        + "\nUSUÁRIO LOGADO INVÁLIDO!\n"
                        + "\n"
                        + "\nPARA CADASTRAR É NECESSÁRIO LOGAR!\n"
                        + "\n"
                        + "\nOK PARA PROSSEGUIR"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
    }
   
    private void verificar_Usuario_Do_Sistema_Que_Cadastrou_E_Alterou(){   
        
        List<Produtos> Produtos = null;
        try{ 
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM JM.PRODUTOS WHERE ID = ?", Produtos.class );
            q.setParameter(1, Classe_Bean_Recebida.getId() );
            List<Produtos> lista_Banco = q.getResultList();   
            Produtos = lista_Banco;
        }catch( Exception e ){ }
        
        String rbusca = ""; 
        try{ rbusca = Produtos.get(0).getDescricao(); }catch( Exception e ){}
            
        if( !rbusca.equals("") ){
                
            //CADASTRO / ALTERAÇÃO - USUÁRIO         
            //try{ Classe_Bean_Recebida.setIdUsuarioSistemaCadastro( Login.Usuario_Logado.getId() ); }catch( Exception e ){}   
            try{ Classe_Bean_Recebida.setIdUsuarioSistemaAlteracao( Login.Usuario_Logado.getId() ); }catch( Exception e ){}   
             
            //CADASTRO / ALTERAÇÃO - DATA - ALTERAÇÃO 
            try{ 
                
                ////////dd.MM.yyyy HH:mm:ss/////////////EEEE, d MMMM yyyy HH:mm:ss
                Calendar calendar = Calendar.getInstance();
                java.util.Date now = calendar.getTime();
                java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());                
                
                DateFormat dfmt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                String dataCadastro = ""; 
                try{ dataCadastro = dfmt.format(currentTimestamp);  }catch(Exception e){}
                ////////dd.MM.yyyy HH:mm:ss/////////////EEEE, d MMMM yyyy HH:mm:ss
                
                try{
                    
                    Classe_Bean_Recebida.setDataAlteracao( currentTimestamp ); 
                    //UsuarioSistema_Recebido.setDataUltimaAlteracaoSenha( data );
                    
                }catch(Exception e){}
            }catch(Exception e){}

            System.out.println( "Alterando Produto: " );
                
            verificar_dscricao_do_produto();
        }
        else{

            //CADASTRO / ALTERAÇÃO - USUÁRIO 
            try{ Classe_Bean_Recebida.setIdUsuarioSistemaCadastro( Login.Usuario_Logado.getId() ); }catch( Exception e ){}                
            try{ Classe_Bean_Recebida.setIdUsuarioSistemaAlteracao( Login.Usuario_Logado.getId() ); }catch( Exception e ){}
                
            //CADASTRO / ALTERAÇÃO - DATA - CADASTRANDO 
            try{ 
                
                ////////dd.MM.yyyy HH:mm:ss/////////////EEEE, d MMMM yyyy HH:mm:ss
                Calendar calendar = Calendar.getInstance();
                java.util.Date now = calendar.getTime();
                java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());                
                
                DateFormat dfmt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                String dataCadastro = ""; 
                try{ dataCadastro = dfmt.format(currentTimestamp);  }catch(Exception e){}
                ////////dd.MM.yyyy HH:mm:ss/////////////EEEE, d MMMM yyyy HH:mm:ss
                
                try{

                    Classe_Bean_Recebida.setDataCadastro( currentTimestamp ); 
                    
                    Classe_Bean_Recebida.setDataAlteracao( currentTimestamp ); 
                    
                    //Produto_Recebido.setDataUltimaAlteracaoSenha( currentTimestamp );
                    
                }catch(Exception e){}
            }catch(Exception e){} 

            System.out.println(  "Novo Produto: " );
                
            verificar_dscricao_do_produto();
        }
    }
    
    private void verificar_dscricao_do_produto(){   
        
        String rbusca = ""; 
        try{ rbusca = tfDescricao.getText().trim(); }catch( Exception e ){}

        if( !rbusca.equals("") ){
            
            try{ Classe_Bean_Recebida.setDescricao( tfDescricao.getText().trim().toUpperCase() ); }catch( Exception e ){}
                
            verificar_Repeticao_Codigo_Auxiliar();
        }
        else{
            
            tfDescricao.requestFocus();
                    
            Class<br.com.jmary.home.imagens.Imagens_Internas> clazzHome = br.com.jmary.home.imagens.Imagens_Internas.class;
            JOPM JOptionPaneMod = new JOPM( 1, "CAMPO DESCRIÇÃO\n"
                    + "\n"
                    + "\nO CAMPO DESCRIÇÃO ESTÁ VAZIO!\n"
                    + "\n"
                    + "\nPARA CADASTRAR É NECESSÁRIO INFORMAR UMA DESCRIÇÃO!\n"
                    + "\n"
                    + "\nOK PARA PROSSEGUIR"
                    ,"Class: " + this.getClass().getName(), 
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
    }
    
    private void verificar_Repeticao_Codigo_Auxiliar(){  
        System.out.println("verificar_Repeticao_Codigo_Auxiliar");
        
        String rbusca = ""; 
        try{ rbusca = Classe_Bean_Recebida.getCodigoAuxiliar().trim(); }catch( Exception e ){}
        
        int iDbusca = 0; 
        try{ iDbusca = Classe_Bean_Recebida.getId(); }catch( Exception e ){}
        
        boolean material_ja_Cadastrado = false;
        if( !rbusca.equals("") ){
/////// VERIFICANDO REPETIÇÃO - CÓDIGO AUXILIAR /////////////////////////////////////////////////////////////////////////////////////////////////////////
            try{ 
                Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM JM.PRODUTOS WHERE CODIGO_AUXILIAR = ?", Produtos.class );
                q2.setParameter( 1, rbusca ); 
                List<Produtos> list = q2.getResultList();                    
                    
                for (Produtos list1 : list) {
                        
                    if (list1.getCodigoAuxiliar().equals( rbusca )) {
                            
                        if (list1.getId() == iDbusca ) {
                                                        
                        }
                        else{
                            
                            material_ja_Cadastrado = true;
                            System.out.println("list1.getId() + \" - \" + iDbusca - "+ list1.getId() + " - " + iDbusca );
                            break; 
                        }
                    }
                }
            }catch( Exception e ){}
        } 
/////// VERIFICANDO REPETIÇÃO - CÓDIGO AUXILIAR ////////////////////////////////////////////////////////////////////////////////////////////////////////        
        
        if ( material_ja_Cadastrado == false ) {
            
            try{
                
                cadastrar();
                
            }catch( Exception e ){
                
                System.out.println("Erro ao cadastrar");
                e.printStackTrace();
            }
        }
        else{
            
            Class<br.com.jmary.home.imagens.Imagens_Internas> clazzHome = br.com.jmary.home.imagens.Imagens_Internas.class;
            JOPM JOptionPaneMod = new JOPM( 1, "CAMPO CÓDIGO AUXILIAR\n"
                    + "\n"
                    + "\nO CAMPO CÓDIGO AUXILIAR JÁ EXISTE!\n"
                    + "\n"
                    + "\nINFORME UM NOVO CÓDIGO AUXILIAR QUE NÃO EXISTA CADASTRADO!\n"
                    + "\n"
                    + "\nOK PARA PROSSEGUIR"
                    ,"Class: " + this.getClass().getName(), 
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }

    }
    
    private void cadastrar(){  
        try{
            
            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Classe_Bean_Recebida, JPAUtil.em());
            Produtos Classe_Bean_Cadastrada = (Produtos) DAOGenericoJPA2.gravanovoOUatualizar();
            
            verificar_lista_de_Endereco_imagemExterna(Classe_Bean_Cadastrada);
            
            ///////////////////////////////////////////////////
            if(status_cadastro.equalsIgnoreCase("Cadastrando...")){                
                Classe_Bean_Recebida = Classe_Bean_Cadastrada;
                status_cadastro = "Visualizando...";
                setar_Dados_Iniciais();  
            }
            ///////////////////////////////////////////////////
            
            String rbusca = ""; 
            try{ rbusca = Classe_Bean_Recebida.getDescricao().toUpperCase(); }catch( Exception e ){}
        
            Class<br.com.jmary.home.imagens.Imagens_Internas> clazzHome = br.com.jmary.home.imagens.Imagens_Internas.class;
            JOPM JOptionPaneMod = new JOPM( 1, "CADASTRO\n"
                    + "\n"
                    + "\nSTATUS DO CADASTRO\n"
                    + "\n"
                    + "\nUSUÁRIO "+ rbusca +" CADASTRADO COM SUCESSO!\n"
                    + "\n"
                    + "\nOK PARA PROSSEGUIR"
                    ,"Class: " + this.getClass().getName(), 
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }catch( Exception e ){
            
            System.out.println("Erro ao cadastrar");
            e.printStackTrace();
        }
    }
    
    private void verificar_lista_de_Endereco_imagemExterna(Produtos Produtos_Cadastrado){
        try{
            
            for (int i=0; i < lista_de_Endereco_imagemExterna_.size(); i++) {
                
                File imagem = new File( lista_de_Endereco_imagemExterna_.get(i) ); 
                
                salvar_imagemExterna(Produtos_Cadastrado, imagem);
            }
        }catch( Exception e ){}
    }
    
    private void salvar_imagemExterna(Produtos Produtos_Cadastrado, File imagemExterna_Endereco){ 

        try { 
            
            BufferedImage bufImg = null;
            ImageIcon     icon   = null;
            Image         image  = null;
            try{
                
                bufImg = ImageIO.read( imagemExterna_Endereco );
                icon   = new ImageIcon(bufImg);
                image  = icon.getImage();//ImageIO.read(f);  
            } catch (IOException ex) {}  

            
                String nome = imagemExterna_Endereco.getName();
                String nomeList[] = nome.split(Pattern.quote("."));
                
                ProdutosImagens ProdutosImagens = new ProdutosImagens();
                
                ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();
                String extencao = imagemExterna_Endereco.getPath().substring( imagemExterna_Endereco.getPath().lastIndexOf('.') + 1 ); 
                
                ImageIO.write((BufferedImage)image, extencao, bytesImg);//seta a imagem para bytesImg
                bytesImg.flush();//limpa a variável
                byte[] byteArray = bytesImg.toByteArray();//Converte ByteArrayOutputStream para byte[] 
                bytesImg.close();//fecha a conversão
                
                ProdutosImagens.setImagem(byteArray);
                
                try{ ProdutosImagens.setIdProdutos(Produtos_Cadastrado.getId() ); }catch( Exception e ){}
                try{ ProdutosImagens.setNome( nomeList[0] ); }catch( Exception e ){}
                
                try{
                    DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( ProdutosImagens, JPAUtil.em());
                    ProdutosImagens ProdutosImagens_Cadastrado = (ProdutosImagens) DAOGenericoJPA2.gravanovoOUatualizar();

                }catch( Exception e ){}
                
        } catch( Exception e ){ 
        
            JOPM JOptionPaneMod = new JOPM( 2, "ERRO AO CADASTRAR, 2"
                + "\nNÃO CADASTRADO"
                + "\nNenhum dado CADASTRADO."
                + "\n", "CADASTRAR - salvar_imagemExterna" );
        } 
    }
/// CADASTRANDO ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
       
}