package com.wxsoft.axis2.client;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

public class ToolsInXml {

	public static void main(String[] args) throws JDOMException, IOException {
		// String str = "<?xml version=\"1.0\"
		// encoding=\"utf-8\"?><arg0><ydjy_inpatientfee_jyd_310_1403
		// xmlns=\"http://www.ylzinfo.com/xsd\"><para><skc098>000</skc098></para><para><yae036>20141029</yae036></para><paralist
		// name=\"list03\"><row skc053=\"84\" akc240=\"224\" skc050=\"140\"
		// aka063=\"05\" /><row skc053=\"0\" akc240=\"769\" skc050=\"769\"
		// aka063=\"10\" /><row skc053=\"0\" akc240=\"1104.8\" skc050=\"1104.8\"
		// aka063=\"11\" /><row skc053=\"494.6\" akc240=\"966.8\"
		// skc050=\"472.2\" aka063=\"13\" /><row skc053=\"0\" akc240=\"63.3\"
		// skc050=\"63.3\" aka063=\"02\" /><row skc053=\"30.7\"
		// akc240=\"2830.47\" skc050=\"2799.77\" aka063=\"01\" /><row
		// skc053=\"60\" akc240=\"832\" skc050=\"772\" aka063=\"08\" /><row
		// skc053=\"0\" akc240=\"174\" skc050=\"174\" aka063=\"09\" /><row
		// skc053=\"0\" akc240=\"1564\" skc050=\"1564\" aka063=\"07\" /><row
		// skc053=\"0\" akc240=\"98\" skc050=\"98\" aka063=\"06\"
		// /></paralist><paralist name=\"list05\"><row aka081=\"30.7\"
		// aka061=\"右佐匹克隆片\" aka069=\"1\" akc227=\"30.7\"
		// aka060=\"86901497000019\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"氯化钠注射液\" aka069=\"0\" akc227=\"156.4\"
		// aka060=\"86902180000613\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"大换药\" aka069=\"0\" akc227=\"60\" aka060=\"12060000201\"
		// aka082=\"无\" /><row aka081=\"0\" aka061=\"肢体气压治疗\" aka069=\"0\"
		// akc227=\"156\" aka060=\"34010002402\" aka082=\"无\" /><row
		// aka081=\"5\" aka061=\"三通管国产\" aka069=\"1\" akc227=\"5\"
		// aka060=\"81001700000\" aka082=\"无\" /><row aka081=\"19.8\"
		// aka061=\"一次性口腔护理包国产\" aka069=\"1\" akc227=\"19.8\"
		// aka060=\"81002000000\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"脂肪瘤切除术\" aka069=\"0\" akc227=\"100\" aka060=\"33160200405\"
		// aka082=\"无\" /><row aka081=\"12\" aka061=\"腹部彩色多普勒超声检查\"
		// aka069=\".1\" akc227=\"120\" aka060=\"22030100120\" aka082=\"无\"
		// /><row aka081=\"0\" aka061=\"梅毒螺旋体特异抗体测定(酶联法)\" aka069=\"0\"
		// akc227=\"60\" aka060=\"25040305302\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"血浆乳酸测定\" aka069=\"0\" akc227=\"12.75\"
		// aka060=\"25030200803\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"数字化摄影(DR)\" aka069=\"0\" akc227=\"60\"
		// aka060=\"21010201501\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"血清总蛋白测定\" aka069=\"0\" akc227=\"4.25\"
		// aka060=\"25030100101\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"乙型肝炎表面抗体测定(Anti-HBs)(化学发光法)\" aka069=\"0\" akc227=\"13.6\"
		// aka060=\"25040300502\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"血清甘油三酯测定\" aka069=\"0\" akc227=\"8.5\"
		// aka060=\"25030300200\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"血细胞分析(五分类)\" aka069=\"0\" akc227=\"30\"
		// aka060=\"25010101505\" aka082=\"无\" /><row aka081=\"8.832\"
		// aka061=\"盐酸氨溴索注射液\" aka069=\".1\" akc227=\"88.32\"
		// aka060=\"86978297000058\" aka082=\"无\" /><row aka081=\"7.49\"
		// aka061=\"尼可地尔片\" aka069=\".1\" akc227=\"74.9\"
		// aka060=\"86979189000088\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"静脉采血\" aka069=\"0\" akc227=\"6\" aka060=\"12040000202\"
		// aka082=\"无\" /><row aka081=\"60.5\" aka061=\"一次性雾化器国产\" aka069=\"1\"
		// akc227=\"60.5\" aka060=\"81003800000\" aka082=\"无\" /><row
		// aka081=\"3.6\" aka061=\"一次性鼻导管国产\" aka069=\"1\" akc227=\"3.6\"
		// aka060=\"81002600000\" aka082=\"无\" /><row aka081=\".25\"
		// aka061=\"一次性注射器国产\" aka069=\".1\" akc227=\"2.5\"
		// aka060=\"81003300000\" aka082=\"无\" /><row aka081=\"16.2\"
		// aka061=\"螺旋CT\" aka069=\".1\" akc227=\"162\" aka060=\"21030000120\"
		// aka082=\"无\" /><row aka081=\"13\" aka061=\"四肢血管彩色多普勒超声\"
		// aka069=\".1\" akc227=\"130\" aka060=\"22030200601\" aka082=\"无\"
		// /><row aka081=\"0\" aka061=\"麻醉中监测(氧浓度)加收\" aka069=\"0\" akc227=\"5\"
		// aka060=\"33010001507\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"血清直接胆红素测定\" aka069=\"0\" akc227=\"8.5\"
		// aka060=\"25030500200\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"乙型肝炎e抗原测定(HBeAg)定量加收\" aka069=\"0\" akc227=\"17\"
		// aka060=\"25040300603\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"钙测定(选择电极法)\" aka069=\"0\" akc227=\"5.95\"
		// aka060=\"25030400403\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"活化部分凝血活酶时间测定(APTT)(仪器法)\" aka069=\"0\" akc227=\"21.25\"
		// aka060=\"25020302502\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"乙型肝炎核心抗体测定(Anti-HBc)定量加收\" aka069=\"0\" akc227=\"17\"
		// aka060=\"25040300903\" aka082=\"无\" /><row aka081=\"10.2\"
		// aka061=\"转化糖电解质注射液\" aka069=\".1\" akc227=\"102\"
		// aka060=\"86900841000200\" aka082=\"无\" /><row aka081=\"2.4\"
		// aka061=\"氯芬待因复方片\" aka069=\".1\" akc227=\"24\"
		// aka060=\"86902937000705\" aka082=\"无\" /><row aka081=\"28\"
		// aka061=\"陪床费加收\" aka069=\"1\" akc227=\"28\" aka060=\"11090000106\"
		// aka082=\"无\" /><row aka081=\"0\" aka061=\"引流管引流\" aka069=\"0\"
		// akc227=\"10\" aka060=\"12090000103\" aka082=\"无\" /><row
		// aka081=\"26.2\" aka061=\"胶片国产\" aka069=\"1\" akc227=\"26.2\"
		// aka060=\"82000100000\" aka082=\"无\" /><row aka081=\".47\"
		// aka061=\"一次性敷贴国产（限一次性输液贴）\" aka069=\".1\" akc227=\"4.7\"
		// aka060=\"81004900000\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"皮肤护理\" aka069=\"0\" akc227=\"60\" aka060=\"12010001406\"
		// aka082=\"无\" /><row aka081=\"8\" aka061=\"超声计算机图文报告\" aka069=\".1\"
		// akc227=\"80\" aka060=\"22080000801\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"X线摄影加滤线器加收\" aka069=\"0\" akc227=\"2\"
		// aka060=\"21010200003\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"血清载脂蛋白α测定\" aka069=\"0\" akc227=\"42.5\"
		// aka060=\"25030301300\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"乙型肝炎表面抗原测定(HBsAg)定量加收\" aka069=\"0\" akc227=\"17\"
		// aka060=\"25040300403\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"人免疫缺陷病毒抗体测定(Anti-HIV)(免疫学法)\" aka069=\"0\" akc227=\"60\"
		// aka060=\"25040301902\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"内生肌酐清除率试验(化学法)\" aka069=\"0\" akc227=\"12.75\"
		// aka060=\"25030700301\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"血清总胆固醇测定\" aka069=\"0\" akc227=\"6.8\"
		// aka060=\"25030300100\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"塑料包埋的手术标本检查与诊断\" aka069=\"0\" akc227=\"160\"
		// aka060=\"27030000502\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"手术中使用高频电刀加收\" aka069=\"0\" akc227=\"150\"
		// aka060=\"33000000003\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"乙型肝炎表面抗原测定(HBsAg)(酶免法)\" aka069=\"0\" akc227=\"6\"
		// aka060=\"25040300401\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"血清碳酸氢盐(HCO3)测定(手工法)\" aka069=\"0\" akc227=\"6.8\"
		// aka060=\"25030401001\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"凝血酶时间测定(TT)(仪器法)\" aka069=\"0\" akc227=\"21.25\"
		// aka060=\"25020303502\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"乙型肝炎核心IgM抗体测定(Anti-HBcIgM)(化学发光法)\" aka069=\"0\"
		// akc227=\"13.6\" aka060=\"25040301002\" aka082=\"无\" /><row
		// aka081=\"0\" aka061=\"血清丙氨酸氨基转移酶测定(速率法)\" aka069=\"0\" akc227=\"8.5\"
		// aka060=\"25030500702\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"盐酸布比卡因注射液\" aka069=\"0\" akc227=\"1.5\"
		// aka060=\"86900665000448\" aka082=\"无\" /><row aka081=\"8.44\"
		// aka061=\"羟乙基淀粉130/0.4氯化钠注射液\" aka069=\".1\" akc227=\"84.4\"
		// aka060=\"86904075000296\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"静脉穿刺置管术\" aka069=\"0\" akc227=\"60\" aka060=\"12040001000\"
		// aka082=\"无\" /><row aka081=\"66\" aka061=\"一次性引流袋进口\" aka069=\"1\"
		// akc227=\"66\" aka060=\"81004600001\" aka082=\"无\" /><row
		// aka081=\"14.08\" aka061=\"导管国产\" aka069=\".1\" akc227=\"140.8\"
		// aka060=\"81003500000\" aka082=\"无\" /><row aka081=\"1.76\"
		// aka061=\"真空采血管国产\" aka069=\".1\" akc227=\"17.6\"
		// aka060=\"83000300000\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"血氧饱和度监测\" aka069=\"0\" akc227=\"35\" aka060=\"31070102800\"
		// aka082=\"无\" /><row aka081=\"6\" aka061=\"四肢血管彩色多普勒超声增加两根血管\"
		// aka069=\".1\" akc227=\"60\" aka060=\"22030200602\" aka082=\"无\"
		// /><row aka081=\"6.33\" aka061=\"马栗种子提取物片\" aka069=\".1\"
		// akc227=\"63.3\" aka060=\"86979356000012\" aka082=\"无\" /><row
		// aka081=\"59.5\" aka061=\"长春西汀注射液\" aka069=\".1\" akc227=\"595\"
		// aka060=\"86903257000321\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"注射用头孢呋辛钠\" aka069=\"0\" akc227=\"546\"
		// aka060=\"86900553000048\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"静脉输液两瓶(含两瓶)以上每瓶加收(最多加收5瓶)\" aka069=\"0\" akc227=\"36\"
		// aka060=\"12040000605\" aka082=\"无\" /><row aka081=\"143\"
		// aka061=\"电极进口\" aka069=\"1\" akc227=\"143\" aka060=\"83011300001\"
		// aka082=\"无\" /><row aka081=\"4.2\" aka061=\"一次性床单国产\" aka069=\"1\"
		// akc227=\"4.2\" aka060=\"81000300000\" aka082=\"无\" /><row
		// aka081=\"0\" aka061=\"Ⅱ级护理\" aka069=\"0\" akc227=\"84\"
		// aka060=\"12010000400\" aka082=\"无\" /><row aka081=\"3\"
		// aka061=\"X线计算机体层(CT)扫描超过一张每张加收\" aka069=\".1\" akc227=\"30\"
		// aka060=\"21030000006\" aka082=\"无\" /><row aka081=\"15\"
		// aka061=\"心脏彩色多普勒超声\" aka069=\".1\" akc227=\"150\"
		// aka060=\"22060000402\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"血清载脂蛋白AⅠ测定\" aka069=\"0\" akc227=\"12.75\"
		// aka060=\"25030300700\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"乙型肝炎表面抗原测定(HBsAg)(化学发光法)\" aka069=\"0\" akc227=\"13.6\"
		// aka060=\"25040300402\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"丙型肝炎抗体测定(Anti-HCV)\" aka069=\"0\" akc227=\"25\"
		// aka060=\"25040301400\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"肌酐测定(酶法)\" aka069=\"0\" akc227=\"12.75\"
		// aka060=\"25030700204\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"血浆纤维蛋白原测定(仪器法)\" aka069=\"0\" akc227=\"17\"
		// aka060=\"25020303002\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"ABO红细胞定型\" aka069=\"0\" akc227=\"5\" aka060=\"26000000100\"
		// aka082=\"无\" /><row aka081=\".45\" aka061=\"电极国产\" aka069=\".1\"
		// akc227=\"4.5\" aka060=\"83011300000\" aka082=\"无\" /><row
		// aka081=\"3.65\" aka061=\"一次性敷贴国产（限一次性输液贴）\" aka069=\".1\"
		// akc227=\"36.5\" aka060=\"81004900000\" aka082=\"无\" /><row
		// aka081=\"0\" aka061=\"口腔护理\" aka069=\"0\" akc227=\"30\"
		// aka060=\"12010001402\" aka082=\"无\" /><row aka081=\"56\"
		// aka061=\"普通病房床位费(3人间)\" aka069=\"0\" akc227=\"196\"
		// aka060=\"11090000130\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"氧化雾化\" aka069=\"0\" akc227=\"300\" aka060=\"12070000104\"
		// aka082=\"无\" /><row aka081=\"3.5\" aka061=\"病理彩色图文报告(含图像分析)\"
		// aka069=\".1\" akc227=\"35\" aka060=\"22080000802\" aka082=\"无\"
		// /><row aka081=\"0\" aka061=\"左心功能彩色多普勒超声检查\" aka069=\"0\"
		// akc227=\"100\" aka060=\"22060001002\" aka082=\"无\" /><row
		// aka081=\"0\" aka061=\"血清载脂蛋白B测定\" aka069=\"0\" akc227=\"12.75\"
		// aka060=\"25030300900\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"乙型肝炎表面抗体测定(Anti-HBs)定量加收\" aka069=\"0\" akc227=\"17\"
		// aka060=\"25040300503\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"戊型肝炎抗体测定(Anti-HEV)\" aka069=\"0\" akc227=\"25\"
		// aka060=\"25040301701\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"血清胱抑素(CystatinC)测定\" aka069=\"0\" akc227=\"17\"
		// aka060=\"25030702800\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"血浆D-二聚体测定 (D-Dimer)(荧光酶免法)\" aka069=\"0\" akc227=\"80\"
		// aka060=\"25020306602\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"Rh血型鉴定\" aka069=\"0\" akc227=\"10\" aka060=\"26000000401\"
		// aka082=\"无\" /><row aka081=\"6.05\" aka061=\"盐酸戊乙奎醚注射液\"
		// aka069=\".1\" akc227=\"60.5\" aka060=\"86902042000300\" aka082=\"无\"
		// /><row aka081=\"0\" aka061=\"盐酸利多卡因注射液\" aka069=\"0\" akc227=\".65\"
		// aka060=\"86900665000318\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"咪达唑仑注射液\" aka069=\"0\" akc227=\"25.1\"
		// aka060=\"86901436001152\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"肌肉注射(皮内)\" aka069=\"0\" akc227=\"3\" aka060=\"12040000102\"
		// aka082=\"无\" /><row aka081=\"0\" aka061=\"葡萄糖注射液\" aka069=\"0\"
		// akc227=\"3.8\" aka060=\"86902763000955\" aka082=\"无\" /><row
		// aka081=\"2.5\" aka061=\"电极国产\" aka069=\".1\" akc227=\"25\"
		// aka060=\"83011300000\" aka082=\"无\" /><row aka081=\"17.6\"
		// aka061=\"吻合器国产\" aka069=\".1\" akc227=\"176\" aka060=\"86000100000\"
		// aka082=\"无\" /><row aka081=\"1.87\" aka061=\"一次性留置静脉针国产\"
		// aka069=\".1\" akc227=\"18.7\" aka060=\"81003400000\" aka082=\"无\"
		// /><row aka081=\"0\" aka061=\"中心给氧\" aka069=\"0\" akc227=\"28\"
		// aka060=\"12030000103\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"日常生活能力评定\" aka069=\"0\" akc227=\"20\" aka060=\"34020000300\"
		// aka082=\"无\" /><row aka081=\"0\" aka061=\"麻醉中监测(ST段分析)加收\"
		// aka069=\"0\" akc227=\"2\" aka060=\"33010001503\" aka082=\"无\" /><row
		// aka081=\"0\" aka061=\"血清总胆红素测定\" aka069=\"0\" akc227=\"6.8\"
		// aka060=\"25030500101\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"乙型肝炎e抗体测定(Anti-HBe)定量加收\" aka069=\"0\" akc227=\"17\"
		// aka060=\"25040300703\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"钠测定(酶法)\" aka069=\"0\" akc227=\"8.5\" aka060=\"25030400202\"
		// aka082=\"无\" /><row aka081=\"0\" aka061=\"无机磷测定\" aka069=\"0\"
		// akc227=\"4.25\" aka060=\"25030400500\" aka082=\"无\" /><row
		// aka081=\"0\" aka061=\"血清低密度脂蛋白胆固醇测定\" aka069=\"0\" akc227=\"8.5\"
		// aka060=\"25030300500\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"乙型肝炎核心抗体测定(Anti-HBc)(化学发光法)\" aka069=\"0\" akc227=\"13.6\"
		// aka060=\"25040300902\" aka082=\"无\" /><row aka081=\"4.37\"
		// aka061=\"苯磺酸左旋氨氯地平片\" aka069=\".1\" akc227=\"43.7\"
		// aka060=\"86903447000025\" aka082=\"无\" /><row aka081=\"75.32\"
		// aka061=\"参芎葡萄糖注射液\" aka069=\".1\" akc227=\"753.2\"
		// aka060=\"86905550000138\" aka082=\"无\" /><row aka081=\"4.4\"
		// aka061=\"替米沙坦片\" aka069=\".1\" akc227=\"44\"
		// aka060=\"86978302000080\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"静脉输液\" aka069=\"0\" akc227=\"112\" aka060=\"12040000601\"
		// aka082=\"无\" /><row aka081=\".05\" aka061=\"一次性注射器国产\" aka069=\".1\"
		// akc227=\".5\" aka060=\"81003300000\" aka082=\"无\" /><row
		// aka081=\".8\" aka061=\"一次性采血器国产\" aka069=\"1\" akc227=\".8\"
		// aka060=\"81003200000\" aka082=\"无\" /><row aka081=\".22\"
		// aka061=\"真空采血管国产\" aka069=\".1\" akc227=\"2.2\"
		// aka060=\"83000300000\" aka082=\"无\" /><row aka081=\"34.5\"
		// aka061=\"X线计算机体层(CT)扫描单次多层10层以上螺旋CT每层加收\" aka069=\".1\"
		// akc227=\"345\" aka060=\"21030000005\" aka082=\"无\" /><row
		// aka081=\"0\" aka061=\"心电监测\" aka069=\"0\" akc227=\"35\"
		// aka060=\"31070102200\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"腰麻硬膜外联合阻滞麻醉\" aka069=\"0\" akc227=\"480\"
		// aka060=\"33010000304\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"血清总胆汁酸测定\" aka069=\"0\" akc227=\"25.5\"
		// aka060=\"25030500500\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"甲型肝炎抗体测定(Anti-HAV)\" aka069=\"0\" akc227=\"15\"
		// aka060=\"25040300101\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"尿素测定(酶促动力学法)\" aka069=\"0\" akc227=\"11.05\"
		// aka060=\"25030700104\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"血浆凝血酶原时间测定(PT)(仪器法)\" aka069=\"0\" akc227=\"21.25\"
		// aka060=\"25020302002\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"乙型肝炎核心IgM抗体测定(Anti-HBcIgM)定量加收\" aka069=\"0\" akc227=\"17\"
		// aka060=\"25040301003\" aka082=\"无\" /><row aka081=\"10.26\"
		// aka061=\"注射用甲磺酸罗哌卡因\" aka069=\".1\" akc227=\"102.6\"
		// aka060=\"86904127000526\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"氯化钠注射液\" aka069=\"0\" akc227=\"39\"
		// aka060=\"86902763001303\" aka082=\"无\" /><row aka081=\"60\"
		// aka061=\"关节松动训练\" aka069=\"1\" akc227=\"60\" aka060=\"34020002601\"
		// aka082=\"无\" /><row aka081=\"15\" aka061=\"创面用材料国产\" aka069=\"1\"
		// akc227=\"15\" aka060=\"86006200000\" aka082=\"无\" /><row
		// aka081=\"46.5\" aka061=\"特殊缝线国产\" aka069=\"1\" akc227=\"46.5\"
		// aka060=\"86000300000\" aka082=\"无\" /><row aka081=\"5\"
		// aka061=\"一次性脸盆、大小便器国产\" aka069=\"1\" akc227=\"5\"
		// aka060=\"81000200000\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"住院诊查费\" aka069=\"0\" akc227=\"98\" aka060=\"11020000500\"
		// aka082=\"无\" /><row aka081=\"9\" aka061=\"浅表器官彩色多普勒超声检查\"
		// aka069=\".1\" akc227=\"90\" aka060=\"22030100201\" aka082=\"无\"
		// /><row aka081=\"0\" aka061=\"麻醉中监测\" aka069=\"0\" akc227=\"30\"
		// aka060=\"33010001501\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"血清白蛋白测定\" aka069=\"0\" akc227=\"5.1\" aka060=\"25030100201\"
		// aka082=\"无\" /><row aka081=\"0\" aka061=\"乙型肝炎e抗原测定(HBeAg)(化学发光法)\"
		// aka069=\"0\" akc227=\"13.6\" aka060=\"25040300602\" aka082=\"无\"
		// /><row aka081=\"0\" aka061=\"钾测定(酶法)\" aka069=\"0\" akc227=\"8.5\"
		// aka060=\"25030400102\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"血清尿酸测定\" aka069=\"0\" akc227=\"8.5\" aka060=\"25030700500\"
		// aka082=\"无\" /><row aka081=\"0\" aka061=\"血清高密度脂蛋白胆固醇测定\"
		// aka069=\"0\" akc227=\"8.5\" aka060=\"25030300400\" aka082=\"无\"
		// /><row aka081=\"0\" aka061=\"组织病理学检查与诊断增加一个蜡块\" aka069=\"0\"
		// akc227=\"60\" aka060=\"27030000001\" aka082=\"无\" /><row
		// aka081=\"4.63\" aka061=\"盐酸阿夫唑嗪片\" aka069=\".1\" akc227=\"46.3\"
		// aka060=\"86904012000204\" aka082=\"无\" /><row aka081=\".84\"
		// aka061=\"灭菌注射用水\" aka069=\".1\" akc227=\"8.4\"
		// aka060=\"86902180001535\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"肌肉注射(皮内)\" aka069=\"0\" akc227=\"1\" aka060=\"12040000102\"
		// aka082=\"无\" /><row aka081=\"1.68\" aka061=\"一次性输液器国产\" aka069=\".1\"
		// akc227=\"16.8\" aka060=\"81003000000\" aka082=\"无\" /><row
		// aka081=\"99\" aka061=\"腰麻硬膜外联合套件国产\" aka069=\"1\" akc227=\"99\"
		// aka060=\"85000200000\" aka082=\"无\" /><row aka081=\"2.64\"
		// aka061=\"一次性注射器国产\" aka069=\".1\" akc227=\"26.4\"
		// aka060=\"81003300000\" aka082=\"无\" /><row aka081=\"9\"
		// aka061=\"彩色多普勒超声检查多部位同时做时，增加一个部位加收\" aka069=\".1\" akc227=\"90\"
		// aka060=\"22030100101\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"十二通道心电图检查\" aka069=\"0\" akc227=\"20\"
		// aka060=\"31070100103\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"麻醉中监测(心率变异分析)加收\" aka069=\"0\" akc227=\"2\"
		// aka060=\"33010001502\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"血清间接胆红素测定\" aka069=\"0\" akc227=\"8.5\"
		// aka060=\"25030500300\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"乙型肝炎e抗体测定(Anti-HBe)(化学发光法)\" aka069=\"0\" akc227=\"13.6\"
		// aka060=\"25040300702\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"氯测定(选择电极法)\" aka069=\"0\" akc227=\"5.95\"
		// aka060=\"25030400304\" aka082=\"无\" /><row aka081=\"0\"
		// aka061=\"葡萄糖测定\" aka069=\"0\" akc227=\"4.25\" aka060=\"25030200101\"
		// aka082=\"无\" /><row aka081=\"0\" aka061=\"快速血浆反应素试验(RPR)\"
		// aka069=\"0\" akc227=\"18\" aka060=\"25040305400\" aka082=\"无\"
		// /></paralist><paralist name=\"list02\"><row ykc054=\"491427\"
		// /></paralist><paralist name=\"list04\" /><paralist
		// name=\"list01\"><row aac003=\"栾衡元\" xka101=\"三级甲等\"
		// skc089=\"6162.15\" aab004=\"山西汽车运输集团有限公司\" aae036=\"20141029\"
		// skc077=\"\" xkc021=\"退休\" skc084=\"0\" akc087=\"0\"
		// aae011=\"wangzm01\" akb021=\"第一人民医院\" skc076=\"\" skc065=\"\"
		// skc071=\"\" akc262=\"1294.17\" skc060=\"\" skc078=\"\"
		// xka032=\"非公务员\" akc264=\"8626.37\" skc085=\"0\" skc063=\"\"
		// skc073=\"0\" skc080=\"\" ykc190=\"11989\" akc261=\"1170.05\"
		// skc081=\"\" skc079=\"\" aac004=\"1\" xac004=\"男\" skc083=\"0\"
		// akc252=\"1294.17\" aae072=\"1410297111099\" aka101=\"02\"
		// aka150=\"0\" akc253=\"669.3\" ska032=\"000\" skc062=\"\" skc061=\"\"
		// skc099=\"147\" akc260=\"6162.15\" aab001=\"101896\"
		// bcc002=\"A05707753\" xkc141=\"正常\" akc021=\"002\" aae001=\"2014\"
		// skc064=\"\" skc072=\"0\" akb020=\"40614126-8\" skc082=\"0\"
		// akc141=\"001\" akc023=\"65\" skc066=\"0\" aac001=\"1006104951\"
		// bcc001=\"140302194902011330\" skc070=\"\" yae072=\"109621\"
		// /></paralist></ydjy_inpatientfee_jyd_310_1403></arg0>";
		/*
		 * String str = "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "<SstRequest>" + "<userid>330600600101</userid>" + "<passwd>330600600101</passwd>" + "<funid>H07.01.01.01</funid>" + "<signature>66F2FA0EA70FFDF40B4461BDF229F9E7</signature>" + "<RequestSet>" + "<yyjgdm>330699001</yyjgdm>" + "<zdbh00>330600600101</zdbh00>" + "<kahao0>D7645554</kahao0>" + "<list01
		 * size=\"3\">" + "<row>" + "<tcqh>1</tcqh>" + "<Yybh>2</Yybh>" + "<brbsh>3</brbsh>" + "<list02
		 * size=\"1\">" + "<row>" + "<xtgzh>2</xtgzh>" + "</row>" + "</list02>" + "</row>" + "</list01>" + "</RequestSet>" + "</SstRequest>";
		 */
		String str = "<?xml version=\"1.0\" encoding=\"GBK\"?><SstRequest><userid>ymzy</userid><passwd>1234</passwd><funid>yq_cx_ybxgsjzd_gn</funid><signature></signature><RequestSet><AAA002>行政区划</AAA002></RequestSet></SstRequest>";
		StringReader sr = new StringReader(str);

		InputSource is = new InputSource(sr);
		Map para = new HashMap();
		List lsList = new ArrayList();
		Document doc = (new SAXBuilder()).build(is);
		Element root = doc.getRootElement();
		readNode1(root, "", para, lsList);
		getList(lsList);
		Map map = new HashMap();
		map.put("para", para);
	}

	public static Map getMap2(String xml) throws Exception {
		// String strxml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + xml;
		String strxml = xml;
		StringReader sr = new StringReader(strxml);

		InputSource is = new InputSource(sr);
		Map para = new HashMap();
		List list = new ArrayList();
		Document doc = (new SAXBuilder()).build(is);
		Element root = doc.getRootElement();
		readNode2(root, para, list);
		Map map = new HashMap();
		map.put("para", para);
		map.put("paralist", list);
		return map;
	}

	public static void readNode2(Element root, Map para, List lsList) {
		Map map = new HashMap();
		if (root.getName().equals("RequestSet")) {
			List<Element> para_childNodes = root.getChildren();
			if (para_childNodes.size() > 0) {
				para.put(para_childNodes.get(0).getName(), para_childNodes.get(
						0).getValue());
				para.put(para_childNodes.get(1).getName(), para_childNodes.get(
						1).getValue());
			}
		}
		if (root.getName().equals("row")) {
			List<Element> aa = root.getChildren();
			for (int i = 0; i < aa.size(); i++) {
				String name = aa.get(i).getName();
				String value = aa.get(i).getValue();
				map.put(name, value);
			}
		}
		if (!map.isEmpty()) {
			lsList.add(map);
		}

		if (root == null) {
			return;
		}

		// 获取属性
		List<Attribute> attrs = root.getAttributes();
		if (attrs != null && attrs.size() > 0) {
			for (Attribute attr : attrs) {
				String check = attr.getParent().getName();
				if (check.equals("list")) {
					para.put(String.valueOf(attr.getName()).toLowerCase(), attr
							.getValue());
				} else if (check.equals("row")) {
					map.put(String.valueOf(attr.getName()).toLowerCase(), attr
							.getValue());
				}
			}
		}

		// 获取他的子节点
		List<Element> childNodes = root.getChildren();
		for (Element e : childNodes) {
			readNode2(e, para, lsList);
		}
	}

	/**
	 * 根据XML解析成MAP
	 * 
	 * @param xml
	 * @throws IOException
	 * @throws JDOMException
	 * @throws Exception
	 */
	public static Map getMap(String xml,Class c1) throws Exception {
		String a = xml;
		StringReader reader = new StringReader(a);
		InputSource source = new InputSource(reader);
		SAXBuilder sax = new SAXBuilder();
		Document doc = sax.build(source);
		Element root = doc.getRootElement();
		List<Element> node = root.getChildren();
		Map map = readNode(node,c1);
		// System.out.println(map);
		return map;
	}

	/**
	 * 解析XML子节点
	 * 
	 * @param root
	 * @param prefix
	 * @param para
	 * @param lsList
	 * @throws Exception 
	 */
	public static Map readNode(List<Element> node,Class c1) throws Exception {

		Element el = null;
		Map map = new HashMap();
		for (int i = 0; i < node.size(); i++) {
			List<Element> list = node.get(i).getChildren();
			String name = node.get(i).getName();
			String value = node.get(i).getValue();
			// System.out.println(name);
			if (list.size() > 0) {

				if (name.indexOf("ReturnValue") != -1) {
					List l = (List) readListNode(node.get(i).getChildren(),c1);
					map.put(name, l);
				} else {
					Map map1 = readNode(list,c1);
					map.put(name, map1);
				}

			} else {
				map.put(name, value);
			}
		}
		return map;
	}

	/**
	 * 解析节点中的list
	 * 
	 * @param nodes
	 * @return
	 * @throws Exception 
	 */
	public static List readListNode(List<Element> nodes,Class c1) throws Exception{
		List childL = new ArrayList();

		for (int i = 0; i < nodes.size(); i++) {
			Element node = nodes.get(i) ;
			String name = node.getName();
			if("Resultset".equals(name)){
				Object oBean = c1.newInstance();
				List<Element> list = node.getChildren();
				for (int j = 0; j < list.size(); j++) {
					Element element = list.get(j) ;
					String cName = element.getName();
					String cValue = element.getValue();
					
					setBeanFields(oBean,cName,cValue);
				}
				childL.add(oBean);
			}
		}
		return childL ;
	}
	/**
	 * 利用反射赋bean值
	 * @param c
	 * @param name
	 * @param value
	 * @throws Exception
	 */
	public static void setBeanFields(Object o, String name, Object value) throws Exception {
		Class c = o.getClass();
		Field[] fields = c.getDeclaredFields();// 得到对象中的字段
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
//			Object value = null;
			// 根据字段类型决定结果集中使用哪种get方法从数据中取到数据
//			if (field.getType().equals(String.class)) {
//				value = rs.getString(fieldName);
//				if (value == null) {
//					value = "";
//				}
//			}
//			if (field.getType().equals(int.class)) {
//				value = rs.getInt(fieldName);
//			}
//			if (field.getType().equals(java.util.Date.class)) {
//				value = rs.getDate(fieldName);
//			}
			if(name.equals(fieldName)){
				// 获得属性的首字母并转换为大写，与setXXX对应
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				String setMethodName = "set" + firstLetter + fieldName.substring(1);
				Method setMethod = c.getMethod(setMethodName, new Class[] { field.getType() });
				setMethod.invoke( o, new Object[] { value });// 调用对象的setXXX方法
				break ;
			}
		}
	}

	public static void readNodeCF2(Element root, Map para, List lsList) {

		Map map = new HashMap();
		if (root.getName().equals("RequestSet")) {
			List<Element> para_childNodes = root.getChildren();
			if (para_childNodes.size() > 0) {
				for (int i = 0; i > para_childNodes.size(); i++) {
					Element e = para_childNodes.get(i);
					String nodeName = para_childNodes.get(i).getName();
					if (nodeName.indexOf("list") > 1) {
						readNodeCF2(e, para, null);
					}
					para.put(nodeName, para_childNodes.get(i).getValue());

				}
			}
		}
		if (root.getName().indexOf("list") > 1) {
			List childL = new ArrayList();
			readNodeCF2(root, para, childL);
			para.put(root.getName(), childL);
		}
		if (root.getName().equals("row")) {

			List<Element> aa = root.getChildren();
			for (int i = 0; i < aa.size(); i++) {
				String name = aa.get(i).getName();
				String value = aa.get(i).getValue();
				map.put(name, value);
			}
			lsList.add(map);
		}

		if (root == null) {
			return;
		}

		// 获取属性
		List<Attribute> attrs = root.getAttributes();
		if (attrs != null && attrs.size() > 0) {
			for (Attribute attr : attrs) {
				String check = attr.getParent().getName();
				if (check.equals("list")) {
					para.put(String.valueOf(attr.getName()).toLowerCase(), attr
							.getValue());
				} else if (check.equals("row")) {
					map.put(String.valueOf(attr.getName()).toLowerCase(), attr
							.getValue());
				}
			}
		}

		// 获取他的子节点
		List<Element> childNodes = root.getChildren();
		for (Element e : childNodes) {
			// readNode2(e, para, lsList);
		}
	}

	public static Map getCF(String xml) throws JDOMException, IOException {
		String strxml = xml;
		StringReader sr = new StringReader(strxml);
		InputSource is = new InputSource(sr);
		Map para = new HashMap();
		List list = new ArrayList();
		Document doc = (new SAXBuilder()).build(is);
		Element root = doc.getRootElement();
		readNodeCF(root, para, list);
		Map map = new HashMap();
		map.put("para", para);
		map.put("paralist", list);
		return map;
	}

	public static void readNodeCF(Element root, Map para, List lsList) {
		Map map = new HashMap();
		if (root.getName().equals("RequestSet")) {
			List<Element> para_childNodes = root.getChildren();
			if (para_childNodes.size() > 0) {
				para.put(para_childNodes.get(0).getName(), para_childNodes.get(
						0).getValue());
				para.put(para_childNodes.get(1).getName(), para_childNodes.get(
						1).getValue());
			}
		}
		if (root.getName().equals("row")) {
			List<Element> aa = root.getChildren();
			for (int i = 0; i < aa.size(); i++) {
				String name = aa.get(i).getName();
				String value = aa.get(i).getValue();
				map.put(name, value);
			}
		}
		if (!map.isEmpty()) {
			lsList.add(map);
		}

		if (root == null) {
			return;
		}

		// 获取属性
		List<Attribute> attrs = root.getAttributes();
		if (attrs != null && attrs.size() > 0) {
			for (Attribute attr : attrs) {
				String check = attr.getParent().getName();
				if (check.equals("list")) {
					para.put(String.valueOf(attr.getName()).toLowerCase(), attr
							.getValue());
				} else if (check.equals("row")) {
					map.put(String.valueOf(attr.getName()).toLowerCase(), attr
							.getValue());
				}
			}
		}

		// 获取他的子节点
		List<Element> childNodes = root.getChildren();
		for (Element e : childNodes) {
			readNode2(e, para, lsList);
		}
	}

	public static Map getList1(String xml) throws Exception {
		String strxml = xml;
		StringReader sr = new StringReader(strxml);
		InputSource is = new InputSource(sr);
		Map para = new HashMap();
		List list = new ArrayList();
		Document doc = (new SAXBuilder()).build(is);
		Element root = doc.getRootElement();
		readNode1(root, "", para, list);
		Map paralist = getList(list);
		Map map = new HashMap();
		map.put("para", para);
		map.put("paralist", paralist);
		return map;
	}

	public static void readNode1(Element root, String prefix, Map para,
			List lsList) {
		if (root.getName().equals("RequestSet")) {
			List<Element> para_childNodes = root.getChildren();
			if (para_childNodes.size() > 0) {
				para.put(para_childNodes.get(0).getName(), para_childNodes.get(
						0).getValue());
			}
		}
		if (root == null) {
			return;
		}
		Map map = new HashMap();
		// 获取属性
		List<Attribute> attrs = root.getAttributes();
		if (attrs != null && attrs.size() > 0) {
			for (Attribute attr : attrs) {
				String check = attr.getParent().getName();
				if (check.equals("para")) {
					para.put(String.valueOf(attr.getName()).toLowerCase(), attr
							.getValue());
				} else if (check.equals("paralist") || check.equals("row")) {
					map.put(String.valueOf(attr.getName()).toLowerCase(), attr
							.getValue());
				}
			}
		}
		if (!map.isEmpty()) {
			lsList.add(map);
		}
		// 获取他的子节点
		List<Element> childNodes = root.getChildren();
		prefix += "\t 11";
		for (Element e : childNodes) {
			readNode1(e, prefix, para, lsList);
		}
	}

	public static Map getList(List list) {
		if (list.size() > 0) {
			int count = 0;
			String lString = "";
			Map map = new HashMap();
			Map mapStr = new HashMap();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map2 = (Map) iterator.next();
				if (!map2.isEmpty()) {
					if (count == 0) {
						mapStr = new HashMap();
					}
					if (map2.containsKey("name")) {
						count = 0;
						lString = map2.get("name").toString();
						map.put(lString, null);
					} else {
						count++;
						mapStr.put("row" + count, map2);
					}
					if (count != 0) {
						map.put(lString, mapStr);
					}
				}
			}
			return map;
		}
		return null;
	}
}
