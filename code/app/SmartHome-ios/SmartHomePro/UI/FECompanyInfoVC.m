//
//  FECompanyInfoVC.m
//  SmartHome
//
//  Created by Seven on 15-4-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECompanyInfoVC.h"
#import "FECompany.h"

@interface FECompanyInfoVC ()
@property (strong, nonatomic) IBOutlet UILabel *companyName;
@property (strong, nonatomic) IBOutlet UILabel *companyAddress;
@property (strong, nonatomic) IBOutlet UILabel *compantType;
@property (strong, nonatomic) IBOutlet UILabel *companyFire;
@property (strong, nonatomic) IBOutlet UILabel *companyArea;
@property (strong, nonatomic) IBOutlet UILabel *legalPerson;
@property (strong, nonatomic) IBOutlet UILabel *manager;
@property (strong, nonatomic) IBOutlet UILabel *dutyPerson;

@end

@implementation FECompanyInfoVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self initUI];
}

-(void)initUI{
    self.title = @"基本信息";
    self.companyName.text = self.company.applyName;
    self.companyAddress.text = self.company.companyAddr;
    self.compantType.text = self.company.companyType;
    self.companyFire.text = self.company.fireRisk;
    self.companyArea.text = self.company.buildingArea.stringValue;
    self.legalPerson.text = self.company.legalOfficer;
    self.manager.text = self.company.fireManager;
    self.dutyPerson.text = self.company.fireDuty;
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
