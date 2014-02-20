package com.exedosoft.plat.action.customize.tools.appshare;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.storage.oss.OSSUpload;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.I18n;
import com.exedosoft.plat.util.RestUtil;
import com.exedosoft.plat.util.WriteTxtFile;
import com.exedosoft.tenant.TenancyValues;

public class DOExportApplicationSQL extends DOExportSQL {
	private static final long serialVersionUID = 568992871873045123L;

	private static final String[] BASE_CONTROLLERS = {
			"4028802429ea02a10129ed8e4e570002",
			"402880242a221874012a221b22a20002",
			"090128e25ff040349bdd7e51ee5cd098",
			"0926c605410d49cc8634c35cd7ea111",
			"0926c605410d49cc8634c35cd7ea222",
			"0926c605410d49cc8634c35cd7ea555",
			"0f2929971b1a4155a411887baa4d2a9a",
			"1c70b0d5015440619cf8d217ab7e08e9",
			"297e276a0d1f7763010d1f77e3020000",
			"297e276a0d1f7763010d1f77e3020019",
			"297e276a0d1f7763010d1f77e30200c6",
			"297e276a0d1f7763010d1f77e30200ca",
			"297e276a0d1f7763010d1f77e30200cb",
			"297e276a0d1f7763010d1f77e30200cc",
			"297e276a0d1f7763010d1f77e30200d1",
			"297e276a0d1f7763010d1f77e30200d3",
			"297e276a0d1f7763010d1f77e30200d5",
			"297e276a0d1f7763010d1f77e30200d6",
			"297e276a0d1f7763010d1f77e30200d7",
			"297e276a0d1f7763010d1f77e30200d8",
			"297e276a0d1f7763010d1f77e30200d9",
			"297e276a0d1f7763010d1f77e30200da",
			"297e276a0d1f7763010d1f77e30200db",
			"297e276a0d1f7763010d1f77e30200dc",
			"297e276a0d1f7763010d1f77e30200dd",
			"297e276a0d1f7763010d1f77e30200ec",
			"297e276a0d1f7763010d1f77e3022222",
			"297e276a0d1f7763010d1f77e302qqq", "297e276a0d1f77630xxxx",
			"297e276a0d1f77630xxxx2", "297e276a0d5cd11a010d5cd11a590000",
			"297e276a0e612661010e6127716c0006",
			"297e276a0e871521010e871d07920013",
			"297e276a0e871521010e871da79b0018",
			"297e276a0e871521010e871eb244001e",
			"297e276a0e9ed96c010e9ede32fd0025",
			"297e276a0ecb8205010ecb87aeab0009",
			"297e276a0ed5680f010ed56c0665000e",
			"297e276a0ed7240b010ed736dcdb0011",
			"297e276a0efe482a010efe4fe59a000f",
			"297e276a0efe482a010efe60a1c8002d",
			"297ea5a813a0292c0113a02cc6600003",
			"297ea5a813a0292c0113a02d341d0004",
			"2f19151b59664f1b8c506c1d9f6dc83c",
			"4028802727272a5f0127272a5f020000",
			"4028803613de5ad10113de897ffa000e", "402880f01888555",
			"402880f01888e73401188111", "402880f01888e73401188111Uf",
			"402880f01888e7340118890111", "402880f01888e7340118890222",
			"402880f01888e7340118890300c0000",
			"402880f01888e7340118890300c0001",
			"402880f01888e7340118890300c0002",
			"402880f01888e7340118890300c9555",
			"402880f01888e7340118890300c9666",
			"402880f01888e7340118890300c9777",
			"402880f01888e7340118890300c9778",
			"402880f01888e7340118890300c999",
			"402880f01888e7340118890300c9999",
			"402880f01888e7340118890300c99uf", "402880f01888e73401188903111",
			"402880f01888e73401188903aaaaaaaa", "402880f01888e734011889aaaa",
			"402880f3126bbf0501126be420180005",
			"4028c38111d4752a0111d485ea720003",
			"4028c38111e6a74f0111e8c0ea8b0026",
			"44d6769c48bb41cab270cc9656520899",
			"51b2b854d08a4c528543fcab418edd36",
			"652a39b1570740eeb5d95df087ddf19a",
			"65d30831165546a7b5dfff255729f1f1",
			"6a6c622b5bbd4a82b499bb5c9888ebb5", "70f87e94e37e48cd9349tree",
			"7a04bcaa1adb48978055fd2d6408107a",
			"8ac3e812e1fa478294c52a4485eb8b0f",
			"979a87fccacb4ca9be1998d000c9421c",
			"9e91464419514e458397bcc6d3361d98",
			"a3c243d6901442628f58aa0c8008caae",
			"a3d57e661473499d9a7105585308d48c",
			"b711a6e0807541c68d245230fc1d5f00",
			"c5012efefeeee8ssssf97a44de719f26",
			"c5012ffffffff8ssssf97a44de719f26",
			"c6cd79d77bbd4fb0886037aebd45575e",
			"c7855accb0974aeab6c1c0346ac2ce9f",
			"d5b2a681f3404d9889a4d04ccd77349d",
			"eb5df9524cc2464b98c7b94be7f2ff27",
			"ff8080811059d98a011059ecbedd004f", "ff8080811059d98a01105excel",
			"ff8080811059d98a011common", "ff8080811059d98a011temp_db_form",
			"ff8080811059d98a011temp_db_grid",
			"ff8080811059d98a011temp_db_menu",
			"ff8080811059d98a011temp_db_pane",
			"ff8080811059d98a011temp_db_tree",
			"2c90b18f2f8b103f012f8b448d090005",
			"0ccb3a1e06c64ca9aae12b14f906dd83",
			"2c90b1bb2f99c429012f99effe970002",
			"2c90b18f2f8b103f012f8b117cc50001",
			"2c90b1bb2f9b10cf012f9b12c9090001",
			"2c90b1bb2f9b10cf012f9b132b5d0002",
			"397e276a0d1f7763010d1f77e30200e6",
			"397e276a0d1f7763010d1f77e3021234",
			"0926c605410d49cc8634c35cd7ea333",
			"0926c605410d49cc8634c35cd7ea666",
			"ff8080810faaaaaaaaf85f81ce10006",
			"297e276a0d1f7763010d1f77e30200e0",
			"70f87e94e37e48cd9349216ea6a1b592",
			"eb5df9524cc2464b98c7b94be7f22222",
			"70f87e94e37e48cd9349216ea6a1baaa",
			"297e276a0efa79f8010efa7b652e0004",
			"402880402fc96c5a012fc9b5a8b80012",
			"402880402fc96c5a012fc9b7120c0014",
			"402880f01888e7340118890300c90050",
			"c79a195b638940c584989649e1886508",
			"402880402fc96c5a012fc9bd99110015",
			"649b231caa6548e99188630104cb6cbc", "ff8080810fbb",
			"888665ef2ed244c2a345a", "397e276a0d1f7763010d1f77eccc",
			"70f87e94e37e48cd9349216ea1111", "70f87e94e37e48cd9349216ea6a1b",
			"40288036312398a6013123d091910002",
			"ff8080813130e77e013130e77e4c0000",
			"402880f01888e7340118890300c9a",
			"402881e73574e81a013575a235ef0004", "402880f01888e73401188903aa",
			"40288af011ac669e0111ac67989a000a",
			"402881e8374e8a7401374e8a74870000",
			"402880133753b66b013753cfdf840062",
			"402880133753b66b013753d02b620063",
			"402880133753b66b013753d076740064",
			"402881e637eef1910137eef2e7ac0008",
			"402881ed38c7598d0138c7598d910000",
			"40288013375db07c01375e0965930001",
			"402981e63e445f1e013e44b2911000ab",
			"402981e63e445f1e013e44b30b2d00ae",
			"ff8080813e88634d013e88634d6e0000",
			"ff8080813e88634d013e8864a4bb0001",
			"402829843f3dd904013f3dd904bc0000",
			"ff8080813f4067b0013f4067b0970000",
			"ff8080813f4067b0013f406847520001",
			"4028803b4181ce5b0141821e54450057",
			"402829844188cc14014188d108100001",
			"402981e441aa6d460141aac6654e003e",
			"4028803b419ae2e701419c85e79200fc",
			"4028803b419ae2e701419b757f3000b7",
			"4028803b419ae2e701419b7521e900b1",
			"402881eb3aaa2f9a013aaa5665c3014d",
			"402981e542542d990142542ddc440001",
			"402981e542542d990142542d99bf0000",
			"402981ed4259e9dd014259e9dd8b0000",
			"402981ec4273488c014273488cec0000" };
	private static final List<String> baseControllers;
	private static final String[] BASE_ACTIONCONFIGS = {
			"2028c38111e6a74f0111e8a1410002",
			"2028c38111e6a74f0111e8a14103001f",
			"297e276a0d2eefd6010d2ef0cc9a0001",
			"297e276a0d90983e010d90983e350000",
			"297e276a0d90983e010d90983e35111",
			"297e276a0e7fa551010e7fa747930009", "297e276a0f21ac1f010f21a1",
			"297e276a0f21ac1f010f21a2", "297e276a0f21ac1f010f21a3",
			"297e276a0f21ac1f010f21a4", "297e276a0f21ac1f010f21a5",
			"297e276a0f21ac1f010f21ae4efc0004",
			"297e276a0f21ac1f010f21ae4efc0005",
			"297e276a0f21ac1f010f21ae4efc0006",
			"297e276a0f21ac1f010f21ae4efc0007", "297e276a0f2298d1010f11",
			"297e276a0f2298d1010f23111", "297e276a0f2298d1010f23222",
			"297e276a0f2298d1010f232f5", "297e276a0f2298d1010f232f5c2a004d",
			"297e276a0f2298d1010f23333", "297e276a0f2298d1010f23999",
			"4028802429f985720129f987ad540001",
			"40288031278a100f01278a100f120000",
			"ff8080810f4e17f2010f4e18fb900005",
			"ff8080810f722503010f722ef9380022",
			"ff8080810f722503010f722fa3730026",
			"ff8080810f75a169010f75acf2850044",
			"ff8080810f764612010f7647211a0005",
			"ff8080810f767825010f7678f4690005",
			"ff8080810f8f81a8010f8f837c9a0005",
			"ff8080811058b1a10110598382fc000",
			"ff8080811058b1a10110598382fc028f", "ff80808110ab22210110666666",
			"ff80808110ab22210110ab28cc6a000c",
			"ff80808110ab22210110ab28cc6a111",
			"ff80808110ab22210110ab28cc6a222",
			"ff80808110ab22210110ab28cc6a333",
			"ff80808110ab22210110ab28cc6a555", "ff80808110ab222ladp1",
			"ff80808110ab222ladp2", "ff80808110ab222ladp4",
			"ff80808110ab222ladp3", "2028c38111e6a74f0111e8a1410001",
			"402881e5315654bf01315654bfa10000",
			"402881e5315654bf0131565505450001",
			"ff808081315f82d901315f84a4e50002",
			"ff808081315f82d901315f84eb450003",
			"ff808081315f82d901315f852c360004",
			"ff80808131601b4f0131601ea9f90002",
			"ff80808131937c8001319389037f0001",
			"402881e53198b1a6013198bbbe5e0015",
			"402881ed38c69dec0138c7364831000b",
			"402881ee3b502204013b502dc8ac0021",
			"402880e53d6d50e4013d6d50e44f0000",
			"402981e741b4e5ae0141b4e5ae6e0000",
			"402981e741b4e5ae0141b4e637b50001",
			"402981e5422c917b01422c917b1c0000",
			"402981ee4250529e014250529e0f0000",
			"402981ec426e4d7201426e56b1d00001" };
	private static final List<String> baseActionConfigs;
	private static final String[] BASE_RESOURCES = {
			"40288031286672650128667c4b100002", "jspheader", "workbenchjsp",
			"402829844265e2a6014265e2a6c50000",
			"402829844265e2a6014265e2a7c70001" };
	private static final List<String> baseResources;
	private static final String[] BASE_SCRIPTS = {
			"4028298442666b310142666b31bf0000",
			"402881e438278bc20138278bc2380000",
			"402981ec426e4d7201426e4d72c90000" };
	private static final List<String> baseScripts;
	
	
	private static final String[] BASE_PARTERS = {
		"1",
		"2",
		"5","6","7","8","9"};
	private static final List<String> baseParters;
	static {
		baseControllers = Arrays.asList(BASE_CONTROLLERS);
		baseActionConfigs = Arrays.asList(BASE_ACTIONCONFIGS);
		baseResources = Arrays.asList(BASE_RESOURCES);
		baseScripts = Arrays.asList(BASE_SCRIPTS);
		baseParters = Arrays.asList(BASE_PARTERS);
	}
	
	
	

	@Override
	public String excute() throws ExedoException {

		/**
		 * 首先检查用户名 密码 是否正确。
		 */

		if (DOGlobals.getInstance().getSessoinContext().getFormInstance() == null) {
			this.setEchoValue(I18n.instance().get("No Form Data!"));
			return NO_FORWARD;
		}

		String callSelectAuth = "callType=sa&contextServiceName=eeplat_user_shareid_ws";

		String shareid = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("name");
		String sharesecret = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("password");

		if (shareid == null || sharesecret == null) {

			this.setEchoValue(I18n.instance().get(
					"Need  ShareID and ShareSecret!"));
			return NO_FORWARD;
		}

		
		BOInstance shareUser = null;
		
		try {
			shareUser = RestUtil.selectOne(callSelectAuth + "&shareid="
					+ shareid + "&sharesecret=" + sharesecret);
			
			System.out.println("ShareUser:::::" + shareUser);
			
			if (shareUser == null) {
				this.setEchoValue(I18n.instance().get(
						"ShareID 或者 ShareSecret 不正确!"));
				return NO_FORWARD;
			}
			
			DOGlobals.getInstance().getSessoinContext().getGlobal().putValue("shareUser", shareUser);
		} catch (Exception e) {
			e.printStackTrace();
			this.setEchoValue(I18n.instance()
					.get("ShareID 或者 ShareSecret 不正确!"));
			return NO_FORWARD;
		}

	
		/**
		 * 
		 * WebService Call, EEPlat Share Server. Local ::
		 * http://127.0.0.1:8080/eeplatshare/servicecontroller
		 */

		String token = null;
		try {
			token = RestUtil.getAccessToken(shareid, sharesecret);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (token == null) {

			this.setEchoValue(I18n.instance().get("No Token!"));
			return NO_FORWARD;
		}
		StringBuilder sb = new StringBuilder("<export>");
		DOBO bo = DOBO.getDOBOByName("do_application");
		BOInstance selectApp = bo.getCorrInstance();

		String findbyshareappid = "callType=sa&contextServiceName=eeplat_appshare_findbyshareappid_ws&share_app_id="
				+ selectApp.getUid();

		try {
			BOInstance bi = RestUtil.selectOne(findbyshareappid);

			if (bi != null) {
				
				this.setEchoValue(I18n.instance().get(
						"发布的应用必须原创APP，该应用已经发布到AppShare，不能重复发布!"));
				return NO_FORWARD;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		sb.append("<!--********************Application***********************-->\n");

		
	/////////////////拿到该工程下的所有自建表。
	///////////sql 语句必须做全面的替换。
		
		appendBOInstance(sb, selectApp);

		DOService findBP = DOService
				.getService("DO_BusiPackage_byapplicationuid");

		DOService findBO = DOService.getService("DO_BO_FindByBPUid_Form");

		Transaction t = findBP.currentTransaction();
		List<String> allIDs = new ArrayList<String>();
		DOExportSQL export = new DOExportSQL();
		try {
			t.begin();
			List bps = appendJSONS(sb, "package", findBP);
			// 服务下面的参数

			DOBO aBO = DOBO.getDOBOByName("do_bo");
			
			
			/////find all bo in application
			List<String> allBO = new ArrayList<String>();
			for (Iterator itBp = bps.iterator(); itBp.hasNext();) {
				BOInstance biBP = (BOInstance) itBp.next();
				List bos = findBO.invokeSelect(biBP.getUid());
				for (Iterator itBO = bos.iterator(); itBO.hasNext();) {
					BOInstance biBO = (BOInstance) itBO.next();
					allBO.add(biBO.getName());
				}
			}
			//为了替换，去除掉重复的业务对象名称。
			allBO = getAfterList(allBO);
			
			DOGlobals.getInstance().getSessoinContext().getGlobal().putValue("allbo4application", allBO);

			/////////////Export BO 
			for (Iterator itBp = bps.iterator(); itBp.hasNext();) {
				BOInstance biBP = (BOInstance) itBp.next();
				List bos = findBO.invokeSelect(biBP.getUid());
				for (Iterator itBO = bos.iterator(); itBO.hasNext();) {
					BOInstance biBO = (BOInstance) itBO.next();
					aBO.refreshContext(biBO.getUid());
					export.exportBO(sb, biBO);
				}
			}

			
			
			// //////////////////////////////////////////////////全局的配置
			// /////////////控制器
			DOService allControllerConfigs = DOService
					.getService("DO_UI_Controller_List");
			appendGlobalsConfig(sb, allControllerConfigs, "Controller",
					baseControllers);
			

			// ////////////自定义动作
			DOService allActionConfigs = DOService
					.getService("DO_ActionConfig_List");
			appendGlobalsConfig(sb, allActionConfigs, "ActionConfigs",
					baseActionConfigs);

			// ////////////////////资源
			DOService allResources = DOService.getService("do_resource_list");
			appendGlobalsConfig(sb, allResources, "Resources", baseResources);

			// //////////////////自定义脚本。
			DOService allScript = DOService.getService("DO_BO_Icon_List");
			appendGlobalsConfig(sb, allScript, "Resources", baseScripts);
			
			
			////////////////////////////////
			
			// /////////////编码定义
			DOService codeMains = DOService
					.getService("DO_CODE_Main_List");
			appendGlobalsConfig(sb, codeMains, "CodeMain");

			DOService codeItems = DOService
					.getService("DO_CODE_ITEM_List");
			appendGlobalsConfig(sb, codeItems, "CodeItem");

			DOService codeItemLinkss = DOService
					.getService("DO_CODE_LINKS_List");
			appendGlobalsConfig(sb, codeItemLinkss, "CodeLinks");
			
			///////////////JobConfig
			DOService jobConfigs = DOService
					.getService("do_jobconfig_List");
			appendGlobalsConfig(sb, jobConfigs, "jobConfigs");

			DOService jobClusters = DOService
					.getService("do_jobcluster_List");
			appendGlobalsConfig(sb, jobClusters, "jobClusters");
			
			/////parter
			DOService orgParts = DOService
					.getService("do_org_part_List");
			appendGlobalsConfig(sb, orgParts, "orgParts",baseParters);		
			
			/////process template
			DOService templtes = DOService
					.getService("do_pt_processtemplate_List");
			appendGlobalsConfig(sb, templtes, "processTemplate");
			
			DOService nodes = DOService
					.getService("do_pt_node_List");
			appendGlobalsConfig(sb, nodes, "nodes");
			
			DOService nodedeps = DOService
					.getService("do_pt_node_denpendency_List");
			appendGlobalsConfig(sb, nodedeps, "nodeDeps");
			
			DOService ptVars = DOService
					.getService("do_pt_var_List");
			appendGlobalsConfig(sb, ptVars, "ptVars");
			
			

			t.end();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		}

		sb.append("</export>");

		String fileName = selectApp.getValue("name") + (".sql.xml");
		try {
			
			System.out.println("XML::::::::::::::::::" + sb);
			
			File aLocalFile = new File(DOInstallApplicationSQL.getFileName(fileName));
			
			WriteTxtFile.writeContent(aLocalFile, sb.toString());
			
			OSSUpload.uploadFile("appsmarket", fileName.toString(), "xml", aLocalFile);
//			OSSUpload.uploadFile("appsmarket", fileName.toString(), "xml", sb
//					.length(),
//					new ByteArrayInputStream(sb.toString().getBytes("UTF-8")));

			String callUpdate = "callType=uf&contextServiceName=eeplat_appshare_insert_ws&access_token="
					+ token;

			Map paras = new HashMap();
			paras.put("xml_path", fileName);
			paras.put("app_name", selectApp.getValue("l10n"));
			paras.put("app_desc", selectApp.getValue("description"));
			paras.put("image_path", selectApp.getValue("upload_path"));
			paras.put("share_date", new java.sql.Date(System.currentTimeMillis()).toLocaleString());
			paras.put("share_app_id", selectApp.getUid());

			if (shareUser != null) {
				paras.put("auth_user_name", shareUser.getValue("user_name"));
				paras.put("auth_user_id", shareUser.getValue("objuid"));
			}

			JSONArray jsonArray = null;
			List listData = new ArrayList();
			try {
				RestUtil.call(callUpdate, paras);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.setEchoValue(I18n.instance().get("成功发布到AppShare!"));
		return DEFAULT_FORWARD;

	}

	private static File storeFile(BOInstance selectApp, StringBuilder sb) {

		URL url = DODataSource.class.getResource("/globals.xml");
		String s = url.getPath();
		String s2 = s;
		s = s.substring(0, s2.toLowerCase().indexOf("web-inf"));

		TenancyValues tv = (TenancyValues) DOGlobals.getInstance()
				.getSessoinContext().getTenancyValues();
		s = s + "appshare/";
		if (tv != null) {
			s = s + tv.getTenant().getValue("name");

		}
		File aFile = new File(s);
		aFile.mkdir();

		StringBuffer fileName = new StringBuffer(aFile.getAbsolutePath())
				.append("/").append(selectApp.getValue("name"))
				.append(".sql.xml");

		aFile = new File(fileName.toString());

		try {
			// TODO GOOGLE IO
			// BufferedWriter out = null;
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileName.toString()), "utf-8"));
			out.append(sb.toString());
			out.flush();
			out.close();
		} catch (Exception e) {

			e.printStackTrace();

		}

		return aFile;

	}
	
	private static List<String> getAfterList(List<String> befor) {
		List<String> after = new ArrayList<String>();
		
		after.addAll(befor);
		
		System.out.println("After:::" + after);
		for(String one : befor){
			for(String two : befor){
				if(!one.equals(two)){
					if(two.contains(one)){
						after.remove(two);
					}
				}
			}
		}
		return after;
	}

//	public static void main(String[] args) {
//
////		File aFile = new File("e:/" + "a.txt");
////		System.out.println("SSSSSSSSSSSS::" + aFile.length());
//
//		String token = null;
//		try {
//			token = RestUtil.getAccessToken("a", "abc");
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		String callUpdate = "callType=uf&contextServiceName=eeplat_appshare_insert_ws&access_token="
//				+ token;
//
//		BOInstance selectApp = new BOInstance();
//		selectApp.putValue("l10n", "中国");
//		selectApp.putValue("description", "a");
//		selectApp.putValue("upload_path", "b");
//		selectApp.putValue("id", "abcedwf");
//
//		Map paras = new HashMap();
//		paras.put("xml_path", "test11111111");
//		paras.put("app_name", selectApp.getValue("l10n"));
//		paras.put("app_desc", selectApp.getValue("description"));
//		paras.put("image_path", selectApp.getValue("upload_path"));
//		paras.put("share_date", new java.sql.Date(System.currentTimeMillis()).toLocaleString());
//		paras.put("share_app_id", selectApp.getUid());
//
//		paras.put("auth_user_name", "a");
//		paras.put("auth_user_id", "abcedef");
//
//		JSONArray jsonArray = null;
//		List listData = new ArrayList();
//		try {
//			RestUtil.call(callUpdate, paras);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
	
	public static void main(String[] args){
		
		
		List<String> befor = new ArrayList<String>();
		
		befor.add("sales_order");
		befor.add("sales_customer");
		befor.add("sales_order_info");
		befor.add("sales_customer_a");
		befor.add("sales_o");
		
		List<String> after = getAfterList(befor);
		
		System.out.println("After:::" + after);
	}



}
