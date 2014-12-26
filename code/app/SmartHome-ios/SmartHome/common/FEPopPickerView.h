//
//  FEPopPickerView.h
//  popPicker
//
//  Created by Seven on 14-12-25.
//  Copyright (c) 2014年 Fuego. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol FEPopPickerViewDelegate <NSObject>

@optional
-(void)popPickerDidSelectedIndex:(NSInteger)index;

@end

@protocol FEPopPickerViewDataSource <NSObject>

@required
-(NSInteger)popPickerItemNumber;
-(NSString *)popPickerTitleAtIndex:(NSInteger)index;

@end

@interface FEPopPickerView : UIView

@property (nonatomic, weak) id<FEPopPickerViewDataSource> dataSource;
@property (nonatomic, weak) id<FEPopPickerViewDelegate> delegate;
@property (nonatomic, strong) UILabel *titleLabel;
@property (nonatomic, assign) NSInteger selected;

@end
