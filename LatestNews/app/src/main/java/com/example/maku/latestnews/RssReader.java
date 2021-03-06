package com.example.maku.latestnews;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.maku.latestnews.MyAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;



public class RssReader extends AsyncTask<Void,Void,Void> {
    Context context;
    URL url;
    ArrayList<FeedItem> feedItem;
    String address="http://www.mobilefeeds.performgroup.com/utilities/interviews/techtest/latestnews.xml";
    RecyclerView  recyclerView;
    ProgressDialog progressDialog;

    public RssReader(Context context,RecyclerView  recyclerView) {
        this.context=context;
        this.recyclerView=recyclerView;
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Loading");
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();

        super.onPreExecute();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        ProcessXml(GetData());

        return null;
    }

    private void ProcessXml(Document data) {
        if(data!=null)
        {
            feedItem=new ArrayList<>(); // array list
            Element root=data.getDocumentElement();
            Node channel=root.getChildNodes().item(1);
            NodeList items=channel.getChildNodes();
            for(int i=0;i<items.getLength();i++)
            {
                Node currentChild=items.item(i);
                if(currentChild.getNodeName().equalsIgnoreCase("item"))
                {
                    FeedItem item=new FeedItem();
                    NodeList itemchilds=currentChild.getChildNodes();
                    for(int j=0;j<itemchilds.getLength();j++)
                    {
                        Node currenet=itemchilds.item(j);
                        if(currenet.getNodeName().equalsIgnoreCase("title"))
                        {
                            item.setTitle(currenet.getTextContent());
                        }
                        else if(currenet.getNodeName().equalsIgnoreCase("description"))
                        {
                            item.setDescription(currenet.getTextContent());
                        }
                        else if(currenet.getNodeName().equalsIgnoreCase("pubDate"))
                        {
                            item.setPubDate(currenet.getTextContent());
                        }

                        else if(currenet.getNodeName().equalsIgnoreCase("link"))
                        {
                            item.setLink(currenet.getTextContent());
                        }
                        else if(currenet.getNodeName().equalsIgnoreCase("enclosure"))
                        {
                            String url=currenet.getAttributes().item(1).getTextContent();
                            item.setThumbnailURL(url);
                        }

                    }

                    feedItem.add(item);


                }
            }
        }
    }

    public Document GetData()
    {
        try
        {
            url=new URL(address);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream=connection.getInputStream();
            DocumentBuilderFactory BuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=BuilderFactory.newDocumentBuilder();
            Document xmlDoc=builder.parse(inputStream);
            return xmlDoc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return  null;
        }



    }


    @Override
    protected void onPostExecute(Void aVoid) {
        progressDialog.dismiss();
        super.onPostExecute(aVoid);
        MyAdapter adapter=new MyAdapter(context, feedItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
       // recyclerView.addItemDecoration(new VerticalSpace(50));

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
