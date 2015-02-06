//
//  FETableView.h
//  SmartHome
//
//  Created by Seven on 14-12-29.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>

@class FETableView;

@protocol FETableViewRefreshDelegate <NSObject>

@optional
-(void)beginLoadMore:(FETableView *)tableview;
-(void)beginPullRefresh:(FETableView *)tableview;

@end


@interface FETableView : UITableView

@property (nonatomic, assign) BOOL loadMore;
@property (nonatomic, assign) BOOL pullRefresh;
@property (nonatomic, weak) id<FETableViewRefreshDelegate> refreshDelegate;


-(void)addRefresh;

- (void)startPullDownRefreshing;

/**
 *  停止下拉刷新的方法
 */
- (void)endPullDownRefreshing;

/**
 *  停止上提加载更多的方法
 */
- (void)endLoadMoreRefresing;

/**
 *  没有更多的数据加载
 */
- (void)endMoreOverWithMessage:(NSString *)message;

/**
 *  重置是否有更多翻页数据要加载
 */
- (void)resetLoadMoreStatue:(BOOL)noMoreDataForLoaded;

/**
 *  当加载更多出现网络错误的时候
 */
- (void)handleLoadMoreError;

/**
 *  获取是否下啦刷新中
 *
 *  @return 返回预期结果
 */
- (BOOL)isLoading;

@end
