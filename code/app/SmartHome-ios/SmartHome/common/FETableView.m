//
//  FETableView.m
//  SmartHome
//
//  Created by Seven on 14-12-29.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FETableView.h"
#import <XHRefreshControl/XHRefreshControl.h>

@interface FETableView ()<XHRefreshControlDelegate>
@property (nonatomic, strong) XHRefreshControl *refreshControl;

@end

@implementation FETableView

-(id)initWithFrame:(CGRect)frame{
    self = [super initWithFrame:frame];
    if (self) {
        [self setTableFooterView:[UIView new]];
    }
    return self;
}

-(id)initWithCoder:(NSCoder *)aDecoder{
    self = [super initWithCoder:aDecoder];
    if (self) {
        [self setTableFooterView:[UIView new]];
    }
    return self;
}

-(void)addRefresh{
    self.refreshControl = [[XHRefreshControl alloc] initWithScrollView:self delegate:self];
    [self.refreshControl resetLoadMoreStatue:NO];
}

-(BOOL)isPullDownRefreshed{
    return _pullRefresh;
}

- (BOOL)isLoadMoreRefreshed{
    return _loadMore;
}

- (XHRefreshViewLayerType)refreshViewLayerType{
    return XHRefreshViewLayerTypeOnScrollViews;
}

- (void)startPullDownRefreshing{
    [self.refreshControl startPullDownRefreshing];
}

/**
 *  停止下拉刷新的方法
 */
- (void)endPullDownRefreshing{
    [self.refreshControl endPullDownRefreshing];
}

/**
 *  停止上提加载更多的方法
 */
- (void)endLoadMoreRefresing{
    [self.refreshControl endLoadMoreRefresing];
}

/**
 *  没有更多的数据加载
 */
- (void)endMoreOverWithMessage:(NSString *)message{
    [self.refreshControl endMoreOverWithMessage:message];
}

/**
 *  重置是否有更多翻页数据要加载
 */
- (void)resetLoadMoreStatue:(BOOL)noMoreDataForLoaded{
    [self.refreshControl resetLoadMoreStatue:noMoreDataForLoaded];
}

/**
 *  当加载更多出现网络错误的时候
 */
- (void)handleLoadMoreError{
    [self.refreshControl handleLoadMoreError];
}

/**
 *  获取是否下啦刷新中
 *
 *  @return 返回预期结果
 */
- (BOOL)isLoading{
    return [self.refreshControl isLoading];
}



- (void)beginPullDownRefreshing{
    if ([self.refreshDelegate respondsToSelector:@selector(beginPullRefresh:)]) {
        [self.refreshDelegate beginPullRefresh:self];
    }
}


- (void)beginLoadMoreRefreshing{
    if ([self.refreshDelegate respondsToSelector:@selector(beginLoadMore:)]) {
        [self.refreshDelegate beginLoadMore:self];
    }
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
