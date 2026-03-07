package main.gui;

import main.gui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AdminDashboard extends JFrame {

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel contentPanel = new JPanel(cardLayout);

    public AdminDashboard() throws SQLException {
        setTitle("Judicial Case Manager - Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // Sidebar menu
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0, 1));
        menuPanel.setPreferredSize(new Dimension(200, 0));

        addMenuButton(menuPanel, "Cases", new CaseManagementPanel());
        addMenuButton(menuPanel, "Persons", new PersonManagementPanel());
        addMenuButton(menuPanel, "Evidence", new EvidencePanel());
        addMenuButton(menuPanel, "Hearings", new HearingPanel());
        addMenuButton(menuPanel, "Appeals", new AppealPanel());
        addMenuButton(menuPanel, "CourtRooms", new CourtRoomPanel());
        addMenuButton(menuPanel, "Court Staff", new CourtStaffPanel());
        addMenuButton(menuPanel, "Bail Requests", new BailPanel());
        addMenuButton(menuPanel, "Evidence", new EvidencePanel());
        addMenuButton(menuPanel, "Case History", new CaseHistoryPanel());
        addMenuButton(menuPanel, "Settlements", new SettlementPanel());

        // Newly added entity panels
        //addMenuButton(menuPanel, "Respondents", new RespondentPanel());
       // addMenuButton(menuPanel, "Witnesses", new WitnessPanel());
       // addMenuButton(menuPanel, "Police Officers", new PoliceOfficerPanel());
//        addMenuButton(menuPanel, "Petitioners", new PetitionerPanel());

        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    private void addMenuButton(JPanel menuPanel, String name, JPanel panel) {
        JButton button = new JButton(name);
        button.addActionListener(e -> cardLayout.show(contentPanel, name));
        menuPanel.add(button);
        contentPanel.add(panel, name);
    }
}
