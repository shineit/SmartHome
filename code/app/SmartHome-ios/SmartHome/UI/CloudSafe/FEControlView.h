//
//  FEControlView.h
//  SmartHome
//
//  Created by Seven on 14-10-22.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>

@class FEControlView;

@protocol FEControlViewDelegate <NSObject>

@optional
-(void)controlViewDidSelectAllOpen:(FEControlView *)cview;
-(void)controlViewDidSelectAllClose:(FEControlView *)cview;

@end

@interface FEControlView : UIView

@property (nonatomic, weak) id<FEControlViewDelegate> delegate;

-(void)cancelSelected;

@end
