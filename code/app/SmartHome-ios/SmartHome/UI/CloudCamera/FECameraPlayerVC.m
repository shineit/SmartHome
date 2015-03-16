//
//  FECameraPlayerVC.m
//  SmartHome
//
//  Created by Seven on 15-2-9.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECameraPlayerVC.h"
#import "YSPlayerController.h"
#import "FECameraVerfyPhoneVC.h"

@interface FECameraPlayerVC ()<YSPlayerControllerDelegate>
@property (strong, nonatomic) IBOutlet UIView *playerView;
@property (strong, nonatomic) YSPlayerController *control;
@property (strong, nonatomic) IBOutlet UIButton *playButton;
@property (strong, nonatomic) IBOutlet UIButton *soundButton;

@end

@implementation FECameraPlayerVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
//    self.playerView
    self.control = [[YSPlayerController alloc] initWithDelegate:self];
    [self startPlay];
    
}
- (IBAction)play:(id)sender {
    if (self.playButton.selected == YES) {
        [self.control stopPlayback];
        [self.playButton setSelected:NO];
    }else{
        [self startPlay];
    }
}

- (IBAction)sound:(id)sender {
    
}

-(void)startPlay{
    [self.playButton setSelected:YES];
    [self.control startRealPlayWithCamera:self.camera.cameraId accessToken:self.accessToken inView:self.playerView];
}

#pragma mark 
- (void)playerOperationMessage:(YSPlayerMessageType)msgType withValue:(id)value{
    if ([value integerValue] != 200) {
        [self.playButton setSelected:NO];
    }
    if ([value integerValue] == 20005) {
        FECameraVerfyPhoneVC *vc = [[FECameraVerfyPhoneVC alloc] initWithNibName:@"FECameraVerfyPhoneVC" bundle:nil];
//        vc.phoneNumber = 
        [self.navigationController pushViewController:vc animated:YES];
    }
}


- (void)realPlayDidStartedWithDict:(NSDictionary *)dict{
    
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
