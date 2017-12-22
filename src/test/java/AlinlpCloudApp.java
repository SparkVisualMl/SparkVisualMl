import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AlinlpCloudApp {
    public static void main( String[] args ) throws Exception {
        // serviceURL: https://dtplus-cn-shanghai.data.aliyuncs.com/{org_code}/nlp/api/Entity/{Domain}
        String serviceURL = "https://dtplus-cn-shanghai.data.aliyuncs.com/dataplus_68287/nlp/api/Entity/ecommerce";
        String akID = "Or6TPoB2pfHjwOuF";
        String akSecret = "PYKchKHDRAznX04xyZD9Gdg4s3YP27";
        // 填充请求body
        // String postBody = "{\"text\":\"真丝韩都衣舍连衣裙\", \"type\": \"simple\"}";
        JSONObject postBodyJson = new JSONObject();

        String text = "骶骨弧度中弧\n" +
                "临产或高危情况：\n" +
                "孕妇孕期查唐氏筛查高风险，未行羊水穿刺，于孕7+月在本市中心医院查患有地中海贫血（未见报告单）。孕期无头昏、胸闷、气促、眼花等症状，不伴皮肤瘙痒史，不伴双下肢水肿史，现因39+5周，下腹隐痛2+小时入院。\n";
        // 要处理的文本
        postBodyJson.put("text", text);
        // type: 控制输出样式，"simple" 简单输出; "full" 详细输出，
        // 简单输出包括实体名称和实体类别，详细输出包括实体名称、实体类别、权重和近义词。
        postBodyJson.put("type", "full");
        // Sender代码参考 https://help.aliyun.com/document_detail/shujia/OCR/ocr-api/sender.html
        // String result = Sender.sendPost(serviceURL, postBody, akID, akSecret);
        String result = Sender.sendPost(serviceURL, postBodyJson.toJSONString(), akID, akSecret);
        System.out.println(result);
        try {
            JSONObject resultJson = JSON.parseObject(result);
            JSONArray wordObjs = resultJson.getObject("data", JSONArray.class);
            for(Object wordObj : wordObjs){
                JSONObject wordJson = JSON.parseObject(wordObj.toString());
                String word = wordJson.getString("word"); // 实体名称
                String tag = wordJson.getString("tag"); // 实体类别
                String synonym = wordJson.getString("synonym"); // 近义词
                String weight = wordJson.getString("weight"); // 权重
                System.out.printf("word: %s, tag: %s, synonym: %s, weight: %s\n",
                        word, tag, synonym, weight);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
