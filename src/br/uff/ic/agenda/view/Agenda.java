package br.uff.ic.agenda.view;

import br.uff.ic.agenda.controller.ControleAdicionar;
import br.uff.ic.agenda.controller.ControlePersistencia;
import br.uff.ic.agenda.controller.ControleCarregar;
import br.uff.ic.agenda.controller.ControleRemover;
import br.uff.ic.agenda.controller.ControleSalvar;
import br.uff.ic.agenda.model.Contato;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Agenda extends JFrame {
    
    private static final String ICONE_ADICIONA = "/toolbarButtonGraphics/general/Add16.gif";
    private static final String ICONE_REMOVE = "/toolbarButtonGraphics/general/Delete16.gif";
    
    private final DefaultListModel<Contato> contatos = new DefaultListModel<>();
    private final JList<Contato> listaContatos = new JList<>(contatos);
    private final JTextField campoNome = new JTextField();
    private final JTextField campoTelefone = new JTextField();
    private final JTextField campoEnderecoComercial = new JTextField();
    private final JTextField campoEnderecoResidencial = new JTextField();
    private final JTextArea campoDetalhes = new JTextArea();
    JPanel painelLista = new JPanel(new BorderLayout());

    public Agenda() {
        super("Agenda");        
        montaJanela();
    }

    private void montaJanela() {
        // Criando um painel com a lista de contatos
        listaContatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        painelLista.add(new JScrollPane(listaContatos), BorderLayout.CENTER);
        
        // Criando um painel com os botões sob a lista
        JButton botaoAdicionar = new JButton("Adicionar");
        JButton botaoRemover = new JButton("Remover");
        JButton botaoOrdenar = new JButton("Ordernar");
        JTextField campoPesquisar = new JTextField();
        JPanel painelBotoes = new JPanel(new GridLayout(4, 1));
        painelBotoes.add(campoPesquisar);
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoRemover);
        painelBotoes.add(botaoOrdenar);
        painelLista.add(painelBotoes, BorderLayout.SOUTH);
                        
        // Criando um painel com o nome
        JPanel painelNome = new JPanel(new BorderLayout());
        painelNome.add(new JLabel("Nome:"), BorderLayout.WEST); 
        campoNome.setEnabled(false);
        painelNome.add(campoNome, BorderLayout.CENTER);

        // Criando um painel com o nome
        JPanel painelEnderecoComercial = new JPanel(new BorderLayout());
        painelEnderecoComercial.add(new JLabel("Endereco comercial:"), BorderLayout.WEST);
        campoEnderecoComercial.setEnabled(false);
        painelEnderecoComercial.add(campoEnderecoComercial, BorderLayout.CENTER);

        // Criando um painel com o nome
        JPanel painelEnderecoResidencial = new JPanel(new BorderLayout());
        painelEnderecoResidencial.add(new JLabel("Endereco Residencial:"), BorderLayout.WEST);
        campoEnderecoResidencial.setEnabled(false);
        painelEnderecoResidencial.add(campoEnderecoResidencial, BorderLayout.CENTER);

        // Criando um painel com o telefone
        JPanel painelTelefone = new JPanel(new BorderLayout());
        painelTelefone.add(new JLabel("Telefone:"), BorderLayout.WEST);
        campoTelefone.setEnabled(false);
        painelTelefone.add(campoTelefone, BorderLayout.CENTER);
        
        // Criando um painel que contem tanto o nome quanto o telefone
        JPanel painelCampos = new JPanel(new GridLayout(5, 1));
        painelCampos.add(painelNome);
        painelCampos.add(painelTelefone);
        painelCampos.add(painelEnderecoComercial);
        painelCampos.add(painelEnderecoResidencial);

        // Criando um painel com os detalhes
        JPanel painelDetalhes = new JPanel(new BorderLayout());
        painelDetalhes.setBorder(BorderFactory.createTitledBorder("Detalhes"));
        campoDetalhes.setEnabled(false);
        painelDetalhes.add(new JScrollPane(campoDetalhes), BorderLayout.CENTER);

        // Criando um painel central que combina os campos de texto, a área de texto e os botões
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.add(painelCampos, BorderLayout.NORTH);
        painelCentral.add(painelDetalhes, BorderLayout.CENTER);
        
        // Criando um painel do tipo split, que combina a lista com os demais componentes
        JSplitPane painelPrincipal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, painelLista, painelCentral);
        painelPrincipal.setDividerLocation(200);
        this.setContentPane(painelPrincipal);

        // Configurando os listeners
        listaContatos.addListSelectionListener(new ControleCarregar(listaContatos, campoNome, campoTelefone, campoDetalhes, campoEnderecoComercial, campoEnderecoResidencial));
        botaoAdicionar.addActionListener(new ControleAdicionar(contatos, painelLista));
        botaoRemover.addActionListener(new ControleRemover(listaContatos, contatos));

        botaoOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ListModel contatosModels = listaContatos.getModel();
                List contatos = new ArrayList<Contato>();
                for (int i = 0; i < contatosModels.getSize(); i++) {
                    contatos.add(contatosModels.getElementAt(i));
                }
                Collections.reverse(contatos);

                DefaultListModel newListModel = new DefaultListModel();
                for(int i = 0; i < contatos.size(); i++) {
                    newListModel.addElement(contatos.get(i));
                }

                listaContatos.setModel(newListModel);
            }
        });

        campoPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (int i=0; i < listaContatos.getModel().getSize(); i++) {
                    if (campoPesquisar.getText().equals(listaContatos.getModel().getElementAt(i).getNome()  )) {
                        listaContatos.setSelectedIndex(i);
                        new ControleCarregar(listaContatos, campoNome, campoTelefone, campoDetalhes, campoEnderecoComercial, campoEnderecoResidencial);
                    }
                }
            }
        });

        ControleSalvar salvar = new ControleSalvar(listaContatos, campoNome, campoTelefone, campoDetalhes, campoEnderecoComercial, campoEnderecoResidencial);
        campoNome.addKeyListener(salvar);
        campoTelefone.addKeyListener(salvar);
        campoDetalhes.addKeyListener(salvar);
        this.addWindowListener(new ControlePersistencia(contatos));

        contatos.addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent listDataEvent) {
                Agenda.this.painelLista.setBorder(BorderFactory.createTitledBorder("Contatos " + listaContatos.getModel().getSize()));
            }

            @Override
            public void intervalRemoved(ListDataEvent listDataEvent) {
                System.out.println("Oi");
                Agenda.this.painelLista.setBorder(BorderFactory.createTitledBorder("Contatos " + listaContatos.getModel().getSize()));
            }

            @Override
            public void contentsChanged(ListDataEvent listDataEvent) {
                System.out.println("Oi");
                Agenda.this.painelLista.setBorder(BorderFactory.createTitledBorder("Contatos " + listaContatos.getModel().getSize()));
            }
        });

        // Configuration a janela
        this.pack();
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
            
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        agenda.setVisible(true);
    }
}