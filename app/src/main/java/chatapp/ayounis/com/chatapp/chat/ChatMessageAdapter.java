package chatapp.ayounis.com.chatapp.chat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import chatapp.ayounis.com.chatapp.R;
import chatapp.ayounis.com.chatapp.data.ChatMessage;

public class ChatMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChatMessage> mItems;

    private Context mContext;

    public ChatMessageAdapter(List<ChatMessage> mItems, Context mContext) {
        this.mItems = mItems;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view;

        if(viewType == ChatMessage.TYPE_MESSAGE_RECEIVED){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_message_received,parent,false);
            viewHolder = new ReceivedMessageViewHolder(view);
        }else{
            view = LayoutInflater.from(mContext).inflate(R.layout.item_message_sent,parent,false);
            viewHolder = new SentMessageViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage chatMessage = mItems.get(position);

        if(chatMessage.getType() == ChatMessage.TYPE_MESSAGE_RECEIVED){
            ((ReceivedMessageViewHolder) holder).tvUsername.setText(chatMessage.getUsername());
            ((ReceivedMessageViewHolder) holder).tvMessage.setText(chatMessage.getMessage());
        }else{
            ((SentMessageViewHolder) holder).tvUsername.setText(chatMessage.getUsername());
            ((SentMessageViewHolder) holder).tvMessage.setText(chatMessage.getMessage());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addNewMessage(@NonNull ChatMessage chatMessage){
        mItems.add(chatMessage);
        notifyItemChanged(mItems.size()-1);
    }

    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder{
        TextView tvUsername, tvMessage;

        public ReceivedMessageViewHolder(View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvMessage = itemView.findViewById(R.id.tvMessage);
        }
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder{
        TextView tvUsername, tvMessage;

        public SentMessageViewHolder(View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvMessage = itemView.findViewById(R.id.tvMessage);
        }
    }
}
