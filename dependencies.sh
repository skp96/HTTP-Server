#!/bin/bash
# Update APT to have the latest list of packages
sudo apt update
# Packages for build envionrment: Ruby and Ruby Gems
sudo apt-get install autoconf bison build-essential libssl-dev libyaml-dev libreadline6-dev zlib1g-dev libncurses5-dev libffi-dev libgdbm6 libgdbm-dev libdb-dev
# Install Ruby Gems and Ruby Dev packages for machine
sudo apt-get install rubygems ruby-dev
# Install correct bundler verison
sudo gem install bundler --version 1.17.2
# Install Rbenv and Ruby
git clone https://github.com/rbenv/rbenv.git ~/.rbenv
export PATH="$HOME/.rbenv/bin:$PATH" >> ~/.bashrc
eval "$(rbenv init -)" >> ~/.bashrc
git clone https://github.com/rbenv/ruby-build.git "$(rbenv root)"/plugins/ruby-build
rbenv install 2.5.9
# Set local Ruby version and install project gems
rbenv local 2.5.9
bundle install