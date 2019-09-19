package com.bai.zhouyang.tree.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bai.zhouyang.tree.R;
import com.bai.zhouyang.tree.bean.CellInfo;
import com.bai.zhouyang.tree.bean.Place;
import com.bai.zhouyang.tree.util.Constant;
import com.kelin.scrollablepanel.library.PanelAdapter;
import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.orhanobut.hawk.Hawk;
import com.zhy.zlib.utils.DateUtil;
import com.zhy.zlib.utils.LogUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyang on 19-5-29.
 */

public class ScrollablePanelAdapter extends PanelAdapter {
    private static final int TITLE_TYPE = 4;
    private static final int ROOM_TYPE = 0;
    private static final int DATE_TYPE = 1;
    private static final int ORDER_TYPE = 2;

    Map<String, Map<String, Map<String, Object>>> rows;

    private List<CellInfo> rowInfoList = new ArrayList<>();
    private List<CellInfo> cellInfoList = new ArrayList<>();
    List<Place> placeList = new ArrayList<>();
    private Place place;
    private ScrollablePanel scrollablePanel;

    public void setPlace(Place place, ScrollablePanel scrollablePanel) {
        this.scrollablePanel = scrollablePanel;
        List<Place> tmpPlace = Hawk.get(Constant.PLACE_LIST);
        if (tmpPlace != null)
            placeList.addAll(tmpPlace);
        this.place = place;
    }

    @Override
    public int getRowCount() {
        return rowInfoList.size() + 1;
    }

    @Override
    public int getColumnCount() {
        return cellInfoList.size() + 1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int row, int column) {
        int viewType = getItemViewType(row, column);
        switch (viewType) {
            case DATE_TYPE:
                setDateView(column, (ColumnViewHolder) holder);
                break;
            case ROOM_TYPE:
                setRoomView(row, (RowViewHolder) holder);
                break;
            case ORDER_TYPE:
                setOrderView(row, column, (OrderViewHolder) holder);
                break;
            case TITLE_TYPE:
                ((TitleViewHolder) holder).titleTextView.setText(place.getName());
                break;
            default:
                setOrderView(row, column, (OrderViewHolder) holder);
        }
    }

    public int getItemViewType(int row, int column) {
        if (column == 0 && row == 0) {
            return TITLE_TYPE;
        }
        if (column == 0) {
            return ROOM_TYPE;
        }
        if (row == 0) {
            return DATE_TYPE;
        }
        return ORDER_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case DATE_TYPE:
                return new ColumnViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_date_info, parent, false));
            case ROOM_TYPE:
                return new RowViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_room_info, parent, false));
            case ORDER_TYPE:
                return new OrderViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_order_info, parent, false));
            case TITLE_TYPE:
                return new TitleViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_title, parent, false));
            default:
                break;
        }
        return new OrderViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_order_info, parent, false));
    }


    private void setDateView(int pos, ColumnViewHolder viewHolder) {
        CellInfo cellInfo = cellInfoList.get(pos - 1);
        if (cellInfo != null && pos > 0) {
            viewHolder.columnTextView.setText(cellInfo.getContent());
        }
    }

    private void setRoomView(int pos, RowViewHolder viewHolder) {
        CellInfo rowInfo = rowInfoList.get(pos - 1);
        if (rowInfo != null && pos > 0) {
            viewHolder.rowTextView.setText(rowInfo.getContent());
        }
    }

    private void setOrderView(final int row, final int column, final OrderViewHolder viewHolder) {
        final String x = "tree_" + (column - 1);
        final String y = "row_" + (row - 1);

        if (rows.containsKey(y) && rows.get(y).containsKey(x)) {
            viewHolder.itemView.setClickable(true);
            final Map<String, Object> tree = rows.get(y).get(x);


            if (tree.get("status").equals("1")) {
                viewHolder.statusTextView.setBackgroundResource(R.drawable.shape_dot_green);
            } else viewHolder.statusTextView.setBackgroundResource(R.drawable.shape_dot_red);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(viewHolder.statusTextView.getContext(), "状态:" + (tree.get("status")
                            .equals("1") ? "正常" : "生病") + "\n" + "种植日:"
                            + DateUtil.milliString2String(tree.get("age").toString(), DateUtil.DATE_PATTERN) + "\n" + "品种:白杨树", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            viewHolder.itemView.setClickable(false);
            viewHolder.statusTextView.setBackgroundResource(R.drawable.shape_dot_d8d8d8);
        }


        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (rows.containsKey(y)) {
                    Map<String, Map<String, Object>> oldRow = rows.get(y);
                    if (oldRow.containsKey(x)) {
                        Map oldTree = oldRow.get(x);
                        if (oldTree.get("status").equals("1")) {// 标记生病树
                            oldTree.put("status", "2");
                            viewHolder.statusTextView.setBackgroundResource(R.drawable.shape_dot_red);
                        } else {// 删除树
                            oldRow.remove(x);
                            place.setUsedCount(place.getUsedCount() - 1);
                            viewHolder.statusTextView.setBackgroundResource(R.drawable.shape_dot_d8d8d8);
                        }
                        rows.put(y, oldRow);
                    } else {//旧行 新种树
                        Map<String, Object> newTree = new HashMap<>();
                        newTree.put("age", System.currentTimeMillis() + "");
                        newTree.put("status", "1");
                        oldRow.put(x, newTree);
                        rows.put(y, oldRow);
                        place.setUsedCount(place.getUsedCount() + 1);
                        viewHolder.statusTextView.setBackgroundResource(R.drawable.shape_dot_green);
                    }

                } else {  //新建行   新种树
                    Map<String, Object> newTree = new HashMap<>();
                    newTree.put("age", System.currentTimeMillis() + "");
                    newTree.put("status", "1");
                    Map<String, Map<String, Object>> newRow = new HashMap<>();
                    newRow.put(x, newTree);
                    rows.put(y, newRow);
                    place.setUsedCount(place.getUsedCount() + 1);
                    viewHolder.statusTextView.setBackgroundResource(R.drawable.shape_dot_green);
                }

                for (int j = 0; j < placeList.size(); j++) {
                    if (placeList.get(j).getPlaceId().equals(place.getPlaceId())) {
                        placeList.set(j, place);
                    }
                }
                scrollablePanel.notifyDataSetChanged();
                saveData();
                return true;
            }
        });

    }


    private static class ColumnViewHolder extends RecyclerView.ViewHolder {
        public TextView columnTextView;

        public ColumnViewHolder(View itemView) {
            super(itemView);
            this.columnTextView = (TextView) itemView.findViewById(R.id.column);
        }

    }

    private static class RowViewHolder extends RecyclerView.ViewHolder {
        public TextView rowTextView;

        public RowViewHolder(View view) {
            super(view);
            this.rowTextView = (TextView) view.findViewById(R.id.row);
        }
    }

    private static class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView statusTextView;

        public OrderViewHolder(View view) {
            super(view);
            this.statusTextView = (TextView) view.findViewById(R.id.status);
        }
    }

    private static class TitleViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public TitleViewHolder(View view) {
            super(view);
            this.titleTextView = (TextView) view.findViewById(R.id.title);
        }
    }


    public void setRowInfoList(List<CellInfo> rowInfoList) {
        this.rowInfoList = rowInfoList;
    }

    public void setColumnInfoList(List<CellInfo> cellInfoList) {
        this.cellInfoList = cellInfoList;
    }

    public void setOrdersList(Map<String, Map<String, Map<String, Object>>> rows) {
        this.rows = rows;
        LogUtils.e("rows", rows.toString());

    }

    public void saveData() {
        Hawk.put(Constant.PLACE_LIST, placeList);
        Hawk.put(Constant.PLACE + place.getPlaceId(), rows);
    }
}
