package com.pugwoo.sshx.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.pugwoo.sshx.core.ExpectClient;

/**
 * 2014年2月2日下午8:27:30
 */
public class Main extends Shell {
	private Text txtIp;
	private Text txtPort;
	private Text txtUser;
	private Text txtPasswd;
	private Text txtCmd;
	private Text txtOutput;
	
	private ExpectClient expectClient = null;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			Main shell = new Main(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public Main(Display display) {
		super(display, SWT.SHELL_TRIM);
		
		txtIp = new Text(this, SWT.BORDER);
		txtIp.setText("192.168.56.101");
		txtIp.setBounds(39, 10, 125, 25);
		
		Label lblIp = new Label(this, SWT.NONE);
		lblIp.setBounds(12, 13, 15, 19);
		lblIp.setText("IP:");
		
		Label lblPort = new Label(this, SWT.NONE);
		lblPort.setBounds(176, 13, 29, 19);
		lblPort.setText("port:");
		
		Label lblUser = new Label(this, SWT.NONE);
		lblUser.setBounds(302, 13, 29, 19);
		lblUser.setText("user:");
		
		Label lblPasswd = new Label(this, SWT.NONE);
		lblPasswd.setBounds(428, 13, 48, 19);
		lblPasswd.setText("passwd:");
		
		txtPort = new Text(this, SWT.BORDER);
		txtPort.setText("22");
		txtPort.setBounds(217, 10, 73, 25);
		
		txtUser = new Text(this, SWT.BORDER);
		txtUser.setText("root");
		txtUser.setBounds(343, 10, 73, 25);
		
		txtPasswd = new Text(this, SWT.BORDER | SWT.PASSWORD);
		txtPasswd.setText("123456");
		txtPasswd.setBounds(488, 10, 106, 25);
		
		Label label = new Label(this, SWT.NONE);
		label.setBounds(10, 52, 29, 19);
		label.setText("\u547D\u4EE4:");
		
		txtCmd = new Text(this, SWT.BORDER);
		txtCmd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// 回车执行
				executeCmd();
			}
		});
		txtCmd.setBounds(49, 49, 630, 25);
		
		Button btnExecute = new Button(this, SWT.NONE);
		btnExecute.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 点击执行
				executeCmd();
			}
		});
		btnExecute.setBounds(685, 47, 86, 29);
		btnExecute.setText("\u6267\u884C");
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setBounds(12, 89, 29, 19);
		label_1.setText("\u8F93\u51FA:");
		
		txtOutput = new Text(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		txtOutput.setBounds(49, 89, 722, 386);
		createContents();
	}
	
	private void executeCmd() {
		// 1. 获得ip和port用户名密码等信息并生成ExpectClient对象
		String ip = txtIp.getText();
		String port = txtPort.getText();
		String user = txtUser.getText();
		String passwd = txtPasswd.getText();
		
		try {
			if(expectClient == null) {
				expectClient = new ExpectClient(ip, Integer.valueOf(port), user, passwd);
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String cmd = txtCmd.getText();
		
		try {
			String output = expectClient.execute(cmd);
			txtOutput.setText(output);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(797, 525);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
