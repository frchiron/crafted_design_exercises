package model.stock;

import model.shopping.Items;

public interface Stock {
	StockCheck contains(Items items);
}
