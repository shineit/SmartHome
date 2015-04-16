//
//  FEPlanVC.m
//  SmartHome
//
//  Created by Seven on 15-4-8.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEPlanVC.h"
#import <PhotoZoom/PZPhotoView.h>
#import "FEQueryPlanByIdRequest.h"
#import "FEQueryPlanByIdResponse.h"
#import "FEWebServiceManager.h"
#import "FEFireAlarm.h"
#import <SDWebImage/SDWebImageManager.h>
#import "Define.h"
#import "UIImage+Cover.h"
#import <ZBUtilities/UIImage+LogN.h>
#import "FEFireAlarm.h"


@interface FEPlanVC ()<PZPhotoViewDelegate>
@property (strong, nonatomic) IBOutlet PZPhotoView *planImageView;

@end

@implementation FEPlanVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"定位";
    self.planImageView.photoViewDelegate = self;
    [self requestPlan];
}

-(void)requestPlan{
    __weak typeof(self) weakself = self;
    [weakself displayHUD:@"加载中..."];
    FEQueryPlanByIdRequest *rdata = [[FEQueryPlanByIdRequest alloc] initWithPid:self.alarm.planID];
    [[FEWebServiceManager sharedInstance] requstData:rdata responseclass:[FEQueryPlanByIdResponse class] response:^(NSError *error, id response) {
        FEQueryPlanByIdResponse *rsp = response;
        if (!error && rsp.result.errorCode.integerValue == 0) {
            SDWebImageManager *imanager = [SDWebImageManager sharedManager];
            [imanager downloadImageWithURL:[NSURL URLWithString:kImageURL(rsp.plan.picPath)] options:0 progress:^(NSInteger receivedSize, NSInteger expectedSize) {
                
            } completed:^(UIImage *image, NSError *error, SDImageCacheType cacheType, BOOL finished, NSURL *imageURL) {
                UIImage *coverImage = [image coverPoint:CGPointMake(weakself.alarm.locationX.floatValue, weakself.alarm.locationY.floatValue)];
                if (image && finished) {
                    dispatch_async(dispatch_get_main_queue(), ^{
                        [weakself.planImageView prepareForReuse];
                        [weakself.planImageView displayImage:coverImage];
                    });
                }
                [weakself hideHUD:YES];
            }];
        }
    }];
}


- (void)photoViewDidSingleTap:(PZPhotoView *)photoView{
    
}
- (void)photoViewDidDoubleTap:(PZPhotoView *)photoView{
    
}
- (void)photoViewDidTwoFingerTap:(PZPhotoView *)photoView{
    
}
- (void)photoViewDidDoubleTwoFingerTap:(PZPhotoView *)photoView{
    
}



- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
